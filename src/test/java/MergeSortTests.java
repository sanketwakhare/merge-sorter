import com.sanket.sort.merge.MergeSort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MergeSortTests {

    /*MergeSort<Integer> sorter;

    public MergeSortTests() {
        sorter = new MergeSort<>();
    }

    @Test
    public void testMergeSort1() {
        List<Integer> list1 = List.of(15, 12, 3, 34, 25, 26, 17, 8, 19, 4);
        List<Integer> list2 = List.of(11, 2, 13, 40, 35);

        // sort list1
        List<Integer> sortedList1 = sorter.sort(list1, 0, list1.size() - 1);
        Assertions.assertEquals(sortedList1, List.of(3, 4, 8, 12, 15, 17, 19, 25, 26, 34));

        // sort list2
        List<Integer> sortedList2 = sorter.sort(list2, 0, list2.size() - 1);
        Assertions.assertEquals(sortedList2, List.of(2, 11, 13, 35, 40));

        // merge sorted lists
        List<Integer> result = sorter.merge(sortedList1, sortedList2);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, List.of(2, 3, 4, 8, 11, 12, 13, 15, 17, 19, 25, 26, 34, 35, 40));
    }

    @Test
    public void testMergeSort2() {
        List<Integer> list1 = List.of(15, 12, 3, 34, 25, 26, 17, 8, 19, 4);
        List<Integer> list2 = List.of();

        // sort list1
        List<Integer> sortedList1 = sorter.sort(list1, 0, list1.size() - 1);
        Assertions.assertEquals(sortedList1, List.of(3, 4, 8, 12, 15, 17, 19, 25, 26, 34));

        // sort list2
        List<Integer> sortedList2 = sorter.sort(list2, -1, -1);
        Assertions.assertEquals(sortedList2, List.of());

        List<Integer> result = sorter.merge(sortedList1, sortedList2);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, List.of(3, 4, 8, 12, 15, 17, 19, 25, 26, 34));
    }

    @Test
    public void testMergeSort3() {
        List<Integer> list1 = List.of();
        List<Integer> list2 = List.of(11, 2, 13, 40, 35);

        // sort list1
        List<Integer> sortedList1 = sorter.sort(list1, -1, -1);
        Assertions.assertEquals(sortedList1, List.of());

        // sort list2
        List<Integer> sortedList2 = sorter.sort(list2, 0, list2.size() - 1);
        Assertions.assertEquals(sortedList2, List.of(2, 11, 13, 35, 40));

        List<Integer> result = sorter.merge(sortedList1, sortedList2);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, List.of(2, 11, 13, 35, 40));
    }*/

}
