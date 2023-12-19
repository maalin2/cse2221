import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class QueueI {
    /**
     * java doc lol.
     *
     */
    private static class IntegerLT implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 < o2) {
                return -1;
            } else if (o1 > o2) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private QueueI() {
    }

    /**
     * Reports the smallest integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return the smallest integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * min is in entries(q) and
     * for all x: integer
     *     where (x is in entries(q))
     *   (min <= x)
     * </pre>
     */
    private static int min(Queue<Integer> q) {
        int min = q.front();

        for (Integer number : q) {
            if (number < min) {
                min = number;
            }
        }

        return min;
    }

    /**
     * Reports an array of two {@code int}s with the smallest and the largest
     * integer in the given {@code Queue<Integer>}.
     *
     * @param q
     *            the queue of integer
     * @return an array of two {@code int}s with the smallest and the largest
     *         integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * { minAndMax[0], minAndMax[1] } is subset of entries(q) and
     * for all x: integer
     *     where (x in in entries(q))
     *   (minAndMax[0] <= x <= minAndMax[1])
     * </pre>
     */
    private static int[] minAndMax(Queue<Integer> q) {
        int[] extremes = { q.front(), q.front() };
        // extreme 1 is min extreme 2 is max

        for (Integer number : q) {
            if (number < extremes[0]) {
                extremes[0] = number;
            } else if (number > extremes[1]) {
                extremes[1] = number;
            }
        }
        return extremes;
    }

    /**
     * Reports an array of two {@code int}s with the smallest and the largest
     * integer in the given {@code Queue<Integer>}. Uses Noah's Ark algorithm
     *
     * @param q
     *            the queue of integer
     * @return an array of two {@code int}s with the smallest and the largest
     *         integer in the given queue
     * @requires q /= empty_string
     * @ensures <pre>
     * { minAndMax[0], minAndMax[1] } is subset of entries(q) and
     * for all x: integer
     *     where (x in in entries(q))
     *   (minAndMax[0] <= x <= minAndMax[1])
     * </pre>
     */
    private static int[] minAndMaxNoahsArk(Queue<Integer> q) {
        int[] extremes = { q.front(), q.front() };
        Comparator<Integer> com = new IntegerLT();

        if (q.length() == 2) {
            for (Integer number : q) {
                if (com.compare(number, extremes[1]) < 0) {
                    extremes[0] = number;
                } else if (com.compare(number, extremes[0]) > 0) {
                    extremes[1] = number;
                }

            }
        } else if (q.length() > 2) {
            Queue<Integer> subQ = new Queue1L<>();
            for (int i = q.length(); i > 0; i--) {
                subQ.enqueue(q.front());
                q.rotate(1);
                subQ.enqueue(q.front());

                // checking for absolute extremes from recursively found relative extremes
                int[] relativeExtremes = minAndMaxNoahsArk(subQ);
                if (com.compare(relativeExtremes[0], extremes[0]) < 0) {
                    extremes[0] = relativeExtremes[0];
                } else if (com.compare(relativeExtremes[1], extremes[1]) > 0) {
                    extremes[1] = relativeExtremes[1];
                }
                subQ.clear();
            }
        }
        return extremes;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        Queue<Integer> testQueue = new Queue1L<>();
        final Integer[] testArray = { 5, 1, 3, -2, 6 };
        for (int i = 0; i < testArray.length; i++) {
            testQueue.enqueue(testArray[i]);
        }
        out.println(testQueue);
        out.println(min(testQueue));
        out.println(minAndMax(testQueue)[0] + " " + minAndMax(testQueue)[1]);
        out.println(minAndMaxNoahsArk(testQueue)[0] + " "
                + minAndMaxNoahsArk(testQueue)[1]);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
