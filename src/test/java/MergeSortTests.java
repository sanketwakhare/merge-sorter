import com.sanket.sort.merge.MergeSort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MergeSortTests {

    MergeSort<Integer> sorter;

    public MergeSortTests() {
        sorter = new MergeSort<>();
    }

    @Test
    public void testMergeSort1() {
        List<Integer> list1 = List.of(15, 12, 3, 34, 25, 26, 17, 8, 19, 4);
        List<Integer> list2 = List.of(11, 2, 13, 40, 35);
        List<Integer> result1 = sorter.sort(list1, 0, list1.size() - 1);
        List<Integer> result2 = sorter.sort(list2, 0, list2.size() - 1);
        List<Integer> result = sorter.merge(result1, result2);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, List.of(2, 3, 4, 8, 11, 12, 13, 15, 17, 19, 25, 26, 34, 35, 40));
    }

    @Test
    public void testMergeSort2() {
        List<Integer> list1 = List.of(15, 12, 3, 34, 25, 26, 17, 8, 19, 4);
        List<Integer> list2 = List.of();
        List<Integer> result1 = sorter.sort(list1, 0, list1.size() - 1);
        List<Integer> result2 = sorter.sort(list2, 0, 0);
        List<Integer> result = sorter.merge(result1, result2);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, List.of(3, 4, 8, 12, 15, 17, 19, 25, 26, 34));
    }

    @Test
    public void testMergeSort3() {
        List<Integer> list1 = List.of();
        List<Integer> list2 = List.of(11, 2, 13, 40, 35);
        List<Integer> result1 = sorter.sort(list1, 0, 0);
        List<Integer> result2 = sorter.sort(list2, 0, list2.size() - 1);
        List<Integer> result = sorter.merge(result1, result2);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, List.of(2, 11, 13, 35, 40));
    }

}
