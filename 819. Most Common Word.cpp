string mostCommonWord(string paragraph, vector<string>& banned) {
        
        string s=paragraph;
        map<string,int> arr;
        string temp = "";
        //iterate through the paragraph(i have renamed it as 's') to find all the words present
        for(int i=0;i<s.length();i++){
            //if the character is between the range of lower or upper characters then we add it to the temporary string.
            if((s[i]>='a'&&s[i]<='z')||(s[i]>='A'&&s[i]<='Z')){
                temp += s[i];
            }else{//else if check if temp is empty or not
                if(temp==""){
                    //if temp is empty, it means no word have been found. So continue with loop
                    continue;
                }else{
                    //if temp is not empty, convert all characters to lower case.
                    //increase occurence of that word in the map. intially new element will be zero.
                    transform(temp.begin(),temp.end(),temp.begin(),::tolower);
                    arr[temp]++;
                    //after increasing, reset temp = "" (empty string).
                    temp = "";
                }
            }
        }
        //the following if case if when the paragraph ends without a fullstop.
        if(temp!=""){
            transform(temp.begin(),temp.end(),temp.begin(),::tolower);
            arr[temp]++;
        }
        //now we iterate through the banned vector to see if have counted those words in the map. If yes, we delete that element from map.
        for(int i=0;i<banned.size();i++){
            if(arr.find(banned[i])!=arr.end()){
                arr.erase(banned[i]);  
            }
        }
        //Now we will find the most frequent word
        string word;
        int count = INT_MIN;
        for(auto i:arr){
            if(i.second>count){
                count = i.second;
                word = i.first;
            }
        }
        return word;        
    }
