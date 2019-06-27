package edu.kit.informatik;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This class makes the bucket elimination
 *
 * @author Armand
 * @version 1.0
 */

public class BucketEliminator {

    private LinkedList<Bucket> buckets = new LinkedList<>();


    /**
     * This method sorts the int lists into the proper buckets and starts the bucket elimination
     *
     * @param input          number list in a list
     * @param countVariables number of the highest variable to determine the number of buckets
     */
    public void sortAndStart(LinkedList<LinkedList<Integer>> input, int countVariables) {
        for (int i = 0; i < countVariables; i++) {
            buckets.add(new Bucket(i + 1));
        }

        //sort input clauses
        for (int i = 0; i < input.size(); i++) {
            buckets.get(Math.abs(input.get(i).get(0)) - 1).addNumber(input.get(i));
        }

        checkTautology();
        checkSubsumption();


        //start the bucket elimination and print the result
        OutputUtilities.printResult(bucketElimination());
    }

    /**
     * This method makes the Bucket Elimination
     *
     * @return true if the clause set is satisfiable false if not
     */
    public boolean bucketElimination() {
        for (int i = 0; i < buckets.size(); i++) {
            LinkedList<LinkedList<Integer>> result = buckets.get(i).resolute();

            if (buckets.get(i).getNumbers().size() > 1 && result.size() == 0)
                return false;

            for (int j = 0; j < result.size(); j++) {
                buckets.get(Math.abs(result.get(j).get(0)) - 1).addNumber(result.get(j));
            }

            checkTautology();
            checkSubsumption();
        }

        return true;
    }

    /**
     * This method checks if a tautology in the clauses of the buckets exists and removes them when so
     */
    private void checkTautology() {
        for (int i = 0; i < buckets.size(); i++) {
            for (int j = 0; j < buckets.get(i).getNumbers().size(); j++) {
                for (int k = 0; k < buckets.get(i).getNumbers().get(j).size(); k++) {
                    int tmpNumber = buckets.get(i).getNumbers().get(j).get(k) * -1;
                    if (buckets.get(i).getNumbers().get(j).contains(tmpNumber)) {
                        buckets.get(i).deleteNumber(j);
                        break;
                    }
                }
            }
        }
    }

    /**
     * This method checks if one of the buckets is a subsumption and if there are existing ones they will be removed
     */
    private void checkSubsumption() {
        for (int i = 0; i < buckets.size(); i++) {
            LinkedList<Integer> tmp = new LinkedList<>();
            for (int j = 0; j < buckets.get(i).getNumbers().size() - 1; j++) {
                //check =>
                int counter = 0;
                for (int k = 0; k < buckets.get(i).getNumbers().get(j).size(); k++) {
                    if (buckets.get(i).getNumbers().get(j + 1).contains(buckets.get(i).getNumbers().get(j).get(k))) {
                        counter ++;
                    }
                }
                if (counter == buckets.get(i).getNumbers().get(j).size()) {
                    tmp.add(j);
                    continue;
                }

                //check <=
                counter = 0;
                for (int k = 0; k < buckets.get(i).getNumbers().get(j + 1).size(); k++) {
                    if (buckets.get(i).getNumbers().get(j).contains(buckets.get(i).getNumbers().get(j + 1).get(k))) {
                        counter ++;
                    }
                }
                if (counter == buckets.get(i).getNumbers().get(j + 1).size()) {
                    tmp.add(j + 1);
                    continue;
                }
            }

            for (int j = 0; j < tmp.size(); j++) {
                buckets.get(i).deleteNumber(tmp.get(j));
            }
        }
    }
}
