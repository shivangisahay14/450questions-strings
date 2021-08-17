int lps(string s) {
	    
	    int i=1,len=0;//len=LPS[i-1]
	    vector<int>LPS(s.size());
	    LPS[0]=0;
	    while(i<s.size()){
	        if(s[i]==s[len]){
	            len++;
	            LPS[i]=len;
	            i++;
	        }
	        else{
	            if(len>0){
	                len=LPS[len-1];
	            }
	            else{
	                LPS[i]=0;
	                i++;
	            }
	        }
	        
	    }
	    return LPS[s.size()-1];//length of lps
      if(LPS[s.size()-1]==0){
            return "";
        }
        else{
            return s.substr(0,LPS[s.size()-1]);//actual lps
        }
	}
