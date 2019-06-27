package edu.kit.informatik;

import java.util.LinkedList;

/**
 * This Class checks the input file for wrong input and reads in the data
 *
 * @author armand
 * @version 1.0
 */
public class InputReader {

    /**
     * This method reads in the input and checks it for errors
     *
     * @param path of file
     * @throws InputException when input is not correct
     */
    public void start(String path) throws InputException {
        String[] input = Terminal.readFile(path);
        int pLine = 0;

        boolean found = false;

        // TODO: 25.06.19 regexes should check for integer overflow

        for (int i = 0; i < input.length; i++) {
            if (input[i].matches("p cnf [1-9][0-9]{0,8} [1-9][0-9]{0,8}")) {
                pLine = i;
                found = true;
            }
        }

        if (!found)
            throw new InputException("No correct p-Line found");


        String[] commandLine = input[pLine].split(" ");
        int countVariables = Integer.parseInt(commandLine[2]);
        int countClauses = Integer.parseInt(commandLine[3]);
        // TODO: 27.06.2019 watch that there is the right number of clauses and variables


        // TODO: 25.06.19 check numbers are in right order and finished with 0

        LinkedList<LinkedList<Integer>> inputForAlgorithm = new LinkedList<>();

        for (int i = 0; i < countClauses; i++) {
            inputForAlgorithm.add(new LinkedList<>());
        }

        int counter = 0;
        for (int i = pLine + 1; i < input.length; i++) {
            String[] clause = input[i].split(" ");
            if (clause[0].equals("c"))
                continue;
            for (int j = 0; j < clause.length - 1; j++) { // because last character is 0
                inputForAlgorithm.get(counter).add(Integer.parseInt(clause[j]));
            }
            counter++;
        }

        new BucketEliminator().sortAndStart(inputForAlgorithm, countVariables);
    }
}
