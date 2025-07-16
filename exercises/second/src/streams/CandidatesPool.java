package streams;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CandidatesPool {
    List<Person> candidates;
    
    public List<Person> getCandidates() {
        return candidates;
    }

    public CandidatesPool() {
        this.candidates=new ArrayList<>();
    }

    public void addCandidate(Predicate<Person> pred, Person candidate){
        if (pred.test(candidate)){
            candidates.add(candidate);
        }
    }

    public String toString(){
        return "The candidates are: "+candidates.stream()
                                                .map(p->p.getName())
                                                .collect(Collectors.joining(" "));
    }
}
