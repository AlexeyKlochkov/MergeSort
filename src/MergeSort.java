import java.util.Comparator;

public class MergeSort {

    private static Object[] aux;
    public static final Comparator<Comparable> ASC = new asc();
    private static final Comparator<Comparable> DESC = new desc();

    private static class asc implements Comparator<Comparable> {
        public int compare (Comparable v, Comparable w) {
            return v.compareTo(w);
        }
    }

    private static class desc implements Comparator<Comparable> {
        public int compare (Comparable v, Comparable w) {
            return w.compareTo(v);
        }
    }
    public static boolean less(Comparator c,Object v, Object w) {
        return c.compare(v, w) < 0;
    }

    public static int Sort(Object[] a,Comparator comparator){
        aux = new Object[a.length];
        return MergeSort.Sort(a, 0, a.length - 1, comparator);
    }

    public static int Sort(Object[] a, int lo, int hi, Comparator comparator) {
        int inv = 0;
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            inv += Sort(a, lo, mid, comparator);
            inv += Sort(a, mid + 1, hi,comparator);
            if (less(comparator, a[mid + 1], a[mid]))
                inv += Merge(comparator, a, lo, mid, hi);
        }
        return inv;
    }

    public static int Merge(Comparator c, Object[] a, int lo, int mid, int hi){
        int inversions = 0;
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                   a[k] = aux[j++];
            else if (j > hi)                    a[k] = aux[i++];
            else if (less(c, aux[i], aux[j]))   a[k] = aux[i++];
            else {
                inversions+= mid - i + 1;
                a[k] = aux[j++];
            }
        }
        return inversions;
    }

    public static void main(String[] args) {
        Integer[] arr = {49, 37, 57, 64, 47, 89, 31, 22, 70, 68, 40, 84};
        System.out.println("Number of inversions:" + MergeSort.Sort(arr, ASC));
        for (Integer i:arr) {
            System.out.print(i + " ");
        }
    }
}
