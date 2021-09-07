vector<string> uncommonFromSentences(string s1, string s2) {
        
        istringstream iss(s1+ " " + s2); 
        unordered_map<string, int> freq; 
        string word; 
        while (iss >> word) ++freq[word]; 
        
        vector<string> ans; 
        for (auto x : freq) {
            if (x.second == 1) ans.push_back(x.first); 
        }
        return ans;         
                
    }

vector<string> uncommonFromSentences(string s1, string s2) {
        
        map<string,int> mp1,mp2;        
        
        for(int i=0;i<s1.size();i++){
            string tmp="";
            
            while(i<s1.size() and  (int)s1[i]!=32){
                  tmp+=s1[i];
                i++;
            }
            mp1[tmp]++;
        }
        
        for(int i=0;i<s2.size();i++){
            string tmp1="";
            
            while(i<s2.size()  and (int)s2[i]!=32){
                  tmp1+=s2[i];
                   i++;
            }
            mp2[tmp1]++;
        }
        
        vector<string> ans;
        
        for(auto x: mp1){
            
            if(x.second==1){
                if(mp2.find(x.first)==mp2.end()){
                    ans.push_back(x.first);
                }
            }
        }
        
         for(auto x: mp2){
            
            if(x.second==1){
                if(mp1.find(x.first)==mp1.end()){
                    ans.push_back(x.first);
                }
            }
        }
        
        return ans;        
    }
