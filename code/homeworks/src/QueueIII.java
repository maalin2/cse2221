import java.util.Comparator;

import components.queue.Queue;
import components.queue.Queue2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Homework from 11/08.
 *
 * @author M. Maalin
 *
 * @param <T>
 *            type of member of the queue
 *
 *
 */
public final class QueueIII<T> extends Queue2<T> {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private QueueIII() {
        // no code needed here
    }

    /**
     * Removes and returns the minimum value from {@code q} according to the
     * ordering provided by the {@code compare} method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to compare entries
     * @return the minimum value from {@code q}
     * @updates q
     * @requires <pre>
     * q /= empty_string  and
     *  [the relation computed by order.compare is a total preorder]
     * </pre>
     * @ensures <pre>
     * perms(q * <removeMin>, #q)  and
     *  for all x: string of character
     *      where (x is in entries (q))
     *    ([relation computed by order.compare method](removeMin, x))
     * </pre>
     */
    private static String removeMin(Queue<String> q, Comparator<String> order) {
        String member = " ";
        String nextMember = " ";
        String smallestMember = " ";

        for (int i = 0; i < q.length(); i++) {

            Queue<String> qCopy = q.newInstance();
            qCopy.append(q);

            qCopy.rotate(1);
            member = q.replaceFront(member);
            nextMember = qCopy.replaceFront(nextMember);
            if (order.compare(member, nextMember) < 0) {
                smallestMember = smallestMember.substring(0, 1);
                smallestMember += member;
            } else if (order.compare(member, nextMember) > 0) {
                smallestMember = smallestMember.substring(0, 1);
                smallestMember = nextMember;
            }
            qCopy.rotate(-1);
        }
        if (!smallestMember.equals(" ")) {
            for (String s : q) {
                s = q.replaceFront(s);
                if (s.equals(smallestMember)) {
                    q.dequeue();
                }
                q.rotate(1);
            }
        }

        return smallestMember;
    }

    /**
     * Sorts {@code q} according to the ordering provided by the {@code compare}
     * method from {@code order}.
     *
     * @param q
     *            the queue
     * @param order
     *            ordering by which to sort
     * @updates q
     * @requires [the relation computed by order.compare is a total preorder]
     * @ensures q = [#q ordered by the relation computed by order.compare]
     */
    public static void sort(Queue<String> q, Comparator<String> order) {
        Queue<String> qCopy = q.newInstance();
        qCopy.transferFrom(q);

        while (qCopy.length() != 0) {
            q.enqueue(removeMin(qCopy, order));
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

        out.close();
    }

}
