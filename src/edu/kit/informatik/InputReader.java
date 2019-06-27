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


        for (int i = 0; i < input.length; i++) {
            if (input[i].matches("p cnf [1-9] [1-9][0-9]{0,8}")) {
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

        int startClauses = pLine + 1;
        for (int i = pLine + 1; i < input.length; i++) {
            String[] clause = input[i].split(" ");
            if (clause[0].equals("c"))
                startClauses ++;
            else
                break;
        }

        if (startClauses + countClauses > input.length)
            throw new InputException("wrong number of clauses inputted");

        int counter = 0;
        for (int i = startClauses; i < startClauses + countClauses; i++) {
            String regex = "(-?[1-9] )+0";
            if (input[i].matches(regex)){
                String[] clause = input[i].split(" ");
                int last = 0;
                for (int j = 0; j < clause.length - 1; j++) { // because last character is 0
                    if (Math.abs(Integer.parseInt(clause[j])) >= last) {
                                inputForAlgorithm.get(counter).add(Integer.parseInt(clause[j]));
                            last = Math.abs(Integer.parseInt(clause[j]));
                    } else
                        throw new InputException("Wrong input inserted");
                }
                counter++;
            } else
                throw new InputException("A wrong input is inserted");

        }

        new BucketEliminator().sortAndStart(inputForAlgorithm, countVariables);
    }
}
