bool isvowel( char ch){
        return ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u' || 
               ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U';  
    }
    string reverseVowels(string s) {
        
        int n=s.size();
        if(n==1) return s;
        int j=n-1;
        int i=0;
        
        while(i<j){
            
            if(!isvowel(s[i])){
                i++;
            }
            else if(!isvowel(s[j])){
                j--;
            }
            else{
                swap(s[i++],s[j--]);
            }
            
        }       
        return s;   
               
    }
