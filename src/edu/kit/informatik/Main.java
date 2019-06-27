package edu.kit.informatik;

/**
 * This class is the main Class which starts the InputReader
 *
 * @author Armand
 * @version 1.0
 */

public class Main {

    /**
     * This method starts the InputReader
     *
     * @param args Path of file
     */
    public static void main(String[] args) {

        try {
            new InputReader().start(args[0]);
        } catch (InputException e) {
            Terminal.printLine(e.getMessage());
        }
//        LinkedList<LinkedList<Integer>> i = new LinkedList<>();
//        int[][] k =  {{-1},{1},{1}};
//
//
//
//        for (int j = 0; j < k.length; j++) {
//            i.add(new LinkedList<>());
//        }
//
//        for (int j = 0; j < i.size(); j++) {
//            for (int l = 0; l < k[j].length; l++) {
//                i.get(j).add(k[j][l]);
//            }
//        }
//
//        new BucketEliminator().bucketEliminator(i);
    }
}
