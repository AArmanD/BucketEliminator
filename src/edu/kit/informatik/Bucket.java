package edu.kit.informatik;

import java.util.LinkedList;

/**
 * This class administrates a bucket
 *
 * @author Armand
 * @version 1.0
 */

public class Bucket {
    private int number;
    private LinkedList<LinkedList<Integer>> numbers = new LinkedList<>();

    /**
     * This is the constructor for the Bucket
     *
     * @param number of the Bucket
     */
    public Bucket(int number) {
        this.number = number;
    }

    /**
     * This method adds a number to the numbers List of the Bucket
     *
     * @param number array to add
     */
    public void addNumber(LinkedList<Integer> number) {
        numbers.add(number);
    }


    public void deleteNumber(int number) {
        numbers.remove(number);
    }

    /**
     * This method returns the numbers list of the bucket
     *
     * @return numbers list of the bucket
     */
    public LinkedList<LinkedList<Integer>> getNumbers() {
        return numbers;
    }

    /**
     * This method makes the resolution between the int lists
     *
     * @return array with the resolution results
     */
    public LinkedList<LinkedList<Integer>> resolute() {

        LinkedList<LinkedList<Integer>> notComplementary = new LinkedList<>();
        LinkedList<LinkedList<Integer>> complementary = new LinkedList<>();

        OutputUtilities.printBucket(this.number);


        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).get(0) == this.number)
                notComplementary.add(numbers.get(i));
            else
                complementary.add(numbers.get(i));
        }

        LinkedList<LinkedList<Integer>> resolutedList = new LinkedList<>();
        for (int i = 0; i < notComplementary.size(); i++) {
            for (int j = 0; j < complementary.size(); j++) {
                LinkedList<Integer> temp = new LinkedList<>();
                for (int k = number + 1; k < 6; k++) {
                    if (notComplementary.get(i).contains(k) || complementary.get(j).contains(k)) {
                        temp.add(k);
                    }
                }
                OutputUtilities.printProcess(notComplementary.get(i), complementary.get(j), temp);
                if (temp.size() != 0)
                    resolutedList.add(temp);
            }
        }
        return resolutedList;
    }
}
