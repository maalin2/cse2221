import components.queue.Queue;
import components.queue.Queue2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Homework from 11/07.
 *
 * @param <T>
 *            type of member of the queue
 *
 * @author M. Maalin
 */
public final class QueueII<T> extends Queue2<T> {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private QueueII() {
        // no code needed here
    }

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */
    @Override
    public void flip() {

        Queue<T> thisCopy = this.newInstance();
        for (T member : this) {
            thisCopy.enqueue(member);
        }
        this.transferFrom(thisCopy);
    }

    /**
     * Reverses ("flips") {@code q}.
     *
     * @param q
     *            the queue to flip
     *
     * @updates q
     * @ensures q = rev(#q)
     */
    public static void flip(Queue<String> q) {

        Queue<String> qCopy = q.newInstance();
        for (String member : q) {
            qCopy.enqueue(member);
        }
        q.transferFrom(qCopy);
    }

    /***
     * Reassembles a DNA sequence using greedy re-assembly from fragments.
     *
     * @param sequenceFragments
     *            Individual fragments of the DNA sequence
     *
     */
    public static void genomeReassembly(Queue<String> sequenceFragments) {
        if (sequenceFragments.length() > 1) {

            boolean overlap = false;
            String largestEnd = "";

            for (String fragment : sequenceFragments) {
                sequenceFragments.rotate(1);

                String nextFragment = "";
                nextFragment = sequenceFragments.replaceFront(nextFragment);

                String tmpLargestEnd = "";
                while (!overlap) {
                    for (int i = 0; i < fragment.length(); i++) {
                        if (fragment.charAt(i) == nextFragment
                                .charAt(nextFragment.length() - i)) {
                            overlap = true;
                            tmpLargestEnd += fragment.charAt(i);
                        } else {
                            overlap = false;
                        }
                    }
                }
                if (tmpLargestEnd.length() > largestEnd.length()) {
                    largestEnd = tmpLargestEnd;
                }
                if (!largestEnd.equals(" ")) {
                    fragment = fragment.substring(0,
                            fragment.length() - largestEnd.length());

                    fragment += nextFragment;

                    sequenceFragments.dequeue();
                    sequenceFragments.rotate(sequenceFragments.length() - 1);
                }
            }
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        Queue<String> sampleFragments = new QueueII<String>();
        sampleFragments.enqueue("TATACAT");
        sampleFragments.enqueue("AGCTGTTTTCGTT");
        sampleFragments.enqueue("CACTCCATTTTA");
        sampleFragments.enqueue("CATTTTAGCTGTT");
        sampleFragments.enqueue("TTTCGTTATACAT");
        sampleFragments.enqueue("CTGTTTTCGTTA");
        genomeReassembly(sampleFragments);

        out.close();
    }

}
