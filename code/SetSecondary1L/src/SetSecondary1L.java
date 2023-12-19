import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods {@code add} and {@code remove}
 * for {@code Set}.
 *
 * @param <T>
 *            type of {@code Set} elements
 */
public final class SetSecondary1L<T> extends Set1L<T> {

    /**
     * No-argument constructor.
     */
    public SetSecondary1L() {
        super();
    }

    @Override
    public Set<T> remove(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        Set<T> thisCopy = this.newInstance();

        for (T member : s) {
            if (this.contains(member)) {
                thisCopy.add(this.remove(member));
            }
        }
        return thisCopy;
    }

    @Override
    public void add(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        Set<T> sCopy = s.newInstance();

        while (s.size() != 0) {
            T member = s.removeAny();

            if (this.contains(member)) {
                sCopy.add(member);
            } else {
                this.add(member);
            }
        }

        s.transferFrom(sCopy);
    }

}
