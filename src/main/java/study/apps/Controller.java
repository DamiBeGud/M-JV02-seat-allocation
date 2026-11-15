package study.apps;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
record SeatInput(String id,Integer seats){}
record Allocation(String id,List<Integer> seats){}
@Service
class Domain {
 final Map<String,Allocation> allocations=new HashMap<>();final boolean[] occupied=new boolean[12];
 synchronized Allocation allocate(SeatInput input){
  String id=Rules.id(input.id());Integer n=input.seats();if(n==null||n<1||n>12)throw new Problem(400,"invalid_group");
  if(allocations.containsKey(id))throw new Problem(409,"duplicate_id");
  for(int start=0;start<=12-n;start++){
   boolean free=true;for(int i=start;i<start+n;i++)if(occupied[i])free=false;
   if(free){List<Integer> seats=new ArrayList<>();for(int i=start;i<start+n;i++){occupied[i]=true;seats.add(i+1);}Allocation row=new Allocation(id,List.copyOf(seats));allocations.put(id,row);return row;}
  }throw new Problem(409,"no_contiguous_capacity");
 }
 synchronized Allocation release(String id){Allocation row=allocations.remove(Rules.id(id));if(row==null)throw new Problem(404,"not_found");for(int seat:row.seats())occupied[seat-1]=false;return row;}
 synchronized Map<String,Integer> status(){int available=0;for(boolean value:occupied)if(!value)available++;return Map.of("capacity",12,"available",available);}
}
@RestController
class Controller {
 final Domain domain;Controller(Domain domain){this.domain=domain;}
 @GetMapping("/status") Map<String,Integer> status(){return domain.status();}
 @GetMapping("/health/live") Map<String,String> live(){return Map.of("status","ok");}
 @PostMapping("/allocations") @ResponseStatus(org.springframework.http.HttpStatus.CREATED) Allocation create(@RequestBody SeatInput input){return domain.allocate(input);}
 @PostMapping("/allocations/{id}/release") Allocation release(@PathVariable String id){return domain.release(id);}
}
