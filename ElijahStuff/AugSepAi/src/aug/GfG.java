package aug;

// Java Code to find rank of elements
public class GfG {
     
    // Function to print m Maximum elements
    public static float[] rankify(float A[], int n)
    {
    	
        // Rank Vector
        float R[] = new float[n];
     
        // Sweep through all elements in A
        // for each element count the number
        // of less than and equal elements
        // separately in r and s
        for (int i = 0; i < n; i++) {
            int r = 1, s = 1;
             
            for (int j = 0; j < n; j++) 
            {
                if (j != i && A[j] < A[i])
                    r += 1;
                     
                if (j != i && A[j] == A[i])
                    s += 1;     
            }
         
        // Use formula to obtain  rank
        R[i] = r + (float)(s - 1) / (float) 2;
     
        } 
     
       /* for (int i = 0; i < n; i++) {
            System.out.print(R[i] + "  ");
        }*/
        return R;
         
    }
 
    // Driver code
    public static void main(String args[])
    {
        float A[] = {1, 2, 5, 2, 1, 25, 2};
        int n = A.length;
    	var ord = rankify(A, n);
        for (int i = 0; i < n; i++) {
            System.out.print(A[i] + "    ");
            System.out.println(ord[i]+"    ");
        }
    }
}
 
// This code is contributed by Swetank Modi