public class LongestPalindromeStringApp {
  
  public static int lpSubstring(String str, int start, int end) {
    if(start > end) {
      return 0;
    }
    
    if(start == end) {
      return 1;
    }
    
    if(str.charAt(start) == str.charAt(end)) {
      int lpsRemainingString = end - start - 1;
      
      if(lpsRemainingString == lpSubstring(str, start + 1, end - 1)) {
        return 2 + lpsRemainingString;
      }
    } 
    
    return Math.max(lpSubstring(str, start + 1, end), lpSubstring(str, start, end - 1));
  }
  
  public static int lpSubStringTopDown(String str, int start, int end, Integer[][] arr) {
    if(start > end) {
      return 0;
    }
    
    if(start == end) {
      return 1;
    }
    
    if(arr[start][end] == null) {
      if(str.charAt(start) == str.charAt(end)) {
        int lpsRemainingString = end - start - 1;
        
        if(lpsRemainingString == lpSubStringTopDown(str, start + 1, end - 1, arr)) {
          arr[start][end] =  2 + lpsRemainingString;
          return arr[start][end];
        }
        
      }
      
      arr[start][end] = Math.max(lpSubStringTopDown(str, start, end - 1, arr), lpSubStringTopDown(str, start + 1, end, arr));
    }

    return arr[start][end];
  }

  public static int lpSubstringBottomUp(String str, int n) {
    if(str == "") {
      return 0;
    }
    
    boolean[][] arr = new boolean[n][n];
    int maxLps = 1;
    
    for (int i = 0; i < str.length(); i++) {
      arr[i][i] = true;
    }
      
    for(int i = n - 2; i >= 0; i--) {
      for(int j = i + 1; j < n; j++) {
        
        if(str.charAt(i) == str.charAt(j)) {
          if(arr[i + 1][j - 1] || j - i == 1) {
            arr[i][j] = true;
            maxLps = Math.max(maxLps, j - i + 1);
          }
        }
      }
    }
    
    return maxLps;
  }

  public static void main(String[] args) {
    String str = "bdb";
    
    System.out.println(LongestPalindromeStringApp.lpSubstring(str, 0, str.length() - 1));
    
    Integer[][] arr = new Integer[str.length()][str.length()];
    System.out.println(LongestPalindromeStringApp.lpSubStringTopDown(str, 0, str.length() - 1, arr));
    
    System.out.println(lpSubstringBottomUp(str, str.length()));
  }


















Method 1: Brute Force.
Approach: The simple approach is to check each substring whether the substring is a palindrome or not. To do this first, run three nested loops, 
the outer two loops pick all substrings one by one by fixing the corner characters, 
the inner loop checks whether the picked substring is palindrome or not.


int longestPalSubstr(string str) 
{ 
    // get length of input string 
    int n = str.size(); 
  
    // All substrings of length 1 
    // are palindromes 
    int maxLength = 1, start = 0; 
  
    // Nested loop to mark start and end index 
    for (int i = 0; i < str.length(); i++) { 
        for (int j = i; j < str.length(); j++) { 
            int flag = 1; 
  
            // Check palindrome 
            for (int k = 0; k < (j - i + 1) / 2; k++) 
                if (str[i + k] != str[j - k]) 
                    flag = 0; 
  
            // Palindrome 
            if (flag && (j - i + 1) > maxLength) { 
                start = i; 
                maxLength = j - i + 1; 
            } 
        } 
    } 
  
    cout << "Longest palindrome substring is: "; 
    printSubStr(str, start, start + maxLength - 1); 
  
    // return length of LPS 
    return maxLength; 
} 


Time complexity: O(n^3).
Three nested loops are needed to find the longest palindromic substring in this approach, so the time complexity is O(n^3).
Auxiliary complexity: O(1).
As no extra space is needed.


Method 2: Dynamic Programming. O(n^2)
Approach: The time complexity can be reduced by storing results of sub-problems. The idea is similar to this post.

Maintain a boolean table[n][n] that is filled in bottom up manner.
The value of table[i][j] is true, if the substring is palindrome, otherwise false.
To calculate table[i][j], check the value of table[i+1][j-1], if the value is true and str[i] is same as str[j], then we make table[i][j] true.
Otherwise, the value of table[i][j] is made false.
We have to fill table previously for substring of length = 1 and length =2 because
as we are finding , if table[i+1][j-1] is true or false , so in case of
(i) length == 1 , lets say i=2 , j=2 and i+1,j-1 doesn’t lies between [i , j]
(ii) length == 2 ,lets say i=2 , j=3 and i+1,j-1 again doesn’t lies between [i , j].


int longestPalSubstr(string str) 
{ 
    // get length of input string 
    int n = str.size(); 
  
    // table[i][j] will be false if substring 
    // str[i..j] is not palindrome. 
    // Else table[i][j] will be true 
    bool table[n][n]; 
  
    memset(table, 0, sizeof(table)); 
  
    // All substrings of length 1 
    // are palindromes 
    int maxLength = 1; 
  
    for (int i = 0; i < n; ++i) 
        table[i][i] = true; 
  
    // check for sub-string of length 2. 
    int start = 0; 
    for (int i = 0; i < n - 1; ++i) { 
        if (str[i] == str[i + 1]) { 
            table[i][i + 1] = true; 
            start = i; 
            maxLength = 2; 
        } 
    } 
  
    // Check for lengths greater than 2. 
    // k is length of substring 
    for (int k = 3; k <= n; ++k) { 
        // Fix the starting index 
        for (int i = 0; i < n - k + 1; ++i) { 
            // Get the ending index of substring from 
            // starting index i and length k 
            int j = i + k - 1; 
  
            // checking for sub-string from ith index to 
            // jth index iff str[i+1] to str[j-1] is a 
            // palindrome 
            if (table[i + 1][j - 1] && str[i] == str[j]) { 
                table[i][j] = true; 
  
                if (k > maxLength) { 
                    start = i; 
                    maxLength = k; 
                } 
            } 
        } 
    } 
  
    cout << "Longest palindrome substring is: "; 
    printSubStr(str, start, start + maxLength - 1); 
  
    // return length of LPS 
    return maxLength; 
} 

Method3:
Approach: Dynamic programming solution is already discussed here previous post. 
The time complexity of the Dynamic Programming based solution is O(n^2) and it requires O(n^2) extra space.
We can find the longest palindrome substring in (n^2) time with O(1) extra space. 
The idea is to generate all even length and odd length palindromes and keep track of the longest palindrome seen so far.

To generate odd length palindrome, Fix a centre and expand in both directions for longer palindromes, i.e. fix i (index) as center and two indices as i1 = i+1 and i2 = i-1
Compare i1 and i2 if equal then decrease i2 and increase i1 and find the maximum length.
Use a similar technique to find the even length palindrome.
Take two indices i1 = i and i2 = i-1 and compare characters at i1 and i2 and find the maximum length till all pair of compared characters are equal and store the maximum length.
Print the maximum length.

class Solution {
public:
string longestPalindrome(string s) {
//Manachar's Algorithm
int n = s.size();
int start = 0, maxLen = 1;
for(int i = 0; i < n;) {
int x = i-1, y = i+1;
//for cases example waaw
while(y < n && s[y] == s[i])
y++;
i = y;
//for cases such as waplilpaw
while(x >= 0 && y < n && s[x] == s[y])
{
x--;
y++;
}
if(y-x-1 > maxLen)
{
start = x+1;
maxLen = y-x-1;
}
}
return s.substr(start, maxLen);
}
};
