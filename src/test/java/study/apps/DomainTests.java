package study.apps;
import java.util.*;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
class DomainTests{
 @Test void businessBoundariesAndConflicts(){Domain d=new Domain();assertEquals(java.util.List.of(1,2,3,4,5,6),d.allocate(new SeatInput("a",6)).seats());d.allocate(new SeatInput("b",6));assertThrows(Problem.class,()->d.allocate(new SeatInput("c",1)));d.release("a");assertEquals(6,d.status().get("available"));assertThrows(Problem.class,()->d.release("a"));assertEquals(6,d.allocate(new SeatInput("c",6)).seats().size());}
}
