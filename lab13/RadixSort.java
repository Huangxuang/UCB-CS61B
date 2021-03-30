/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        // TODO: Implement LSD Sort
        //find out the longest string length, call LSD that number of times
        String[] sorted = asciis.clone();
        int maxStringLength = 0;
        for (String s : asciis) {
            if (s.length() > maxStringLength) {
                maxStringLength = s.length();
            }
        }
        for (int i = maxStringLength - 1; i >= 0; i--) {
            sortHelperLSD(sorted,i);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        //create a new array characterAtIthIndex with indexth character in each item of array asciis
        //apply counting sort on the array


        //reserve index 0 to null
        int[] counts = new int[257];
        int[] starts = new int[257];

        //counts array
        for (int i = 0; i < asciis.length; i++ ) {
            int x = helperToFind(index,asciis[i]);
            counts[x] ++;
        }
        //starts array
        int pos = 0;
        for (int i = 0; i < counts.length; i++) {
            starts[i] = pos;
            pos += counts[i];
        }
        //sorted array
        String[] sorted = new String[asciis.length];
        for (int i = 0; i < asciis.length; i++) {
            String item = asciis[i];
            int x = helperToFind(index,item);
            int location = starts[x];
            sorted[location] = item;
            starts[x] ++;
        }

        //copy sorted to asciis
        for (int i = 0; i < asciis.length; i++) {
            asciis[i] = sorted[i];
        }


    }

    private static int helperToFind (int index, String s) {
        int itemToInt;
        if (s.length() <=  index) {
            itemToInt = 0;
        } else {
            itemToInt = s.charAt(index) + 1;
        }
        return itemToInt;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main (String[] args) {
        String[] test = {"asdf","123","aa","asdff","!@#","223"};

        String[] res = RadixSort.sort(test);
        for (String s : res) {
            System.out.println(s +"");
        }

    }
}
