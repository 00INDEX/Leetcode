#include "../LeetcodeHeader.hpp"

void QuickSort(vector<int> &citations, int low, int high){
    if(low >= high){
        return;
    }
    int i = low, j = high;
    int x = citations[i];
    while(i < j){
        while(i < j && citations[j] >= x) j--;
        if(i < j) citations[i++] = citations[j];
        while(i < j && citations[i] < x) i++;
        if(i < j) citations[j--] = citations[i];
    }
    citations[i] = x;
    QuickSort(citations, low, i - 1);
    QuickSort(citations, i + 1, high);
}

int hIndex(vector<int> &citations)
{
    QuickSort(citations, 0, citations.size() - 1);
    for(int j = 0; j < citations.size(); j++){
        //cout << citations[j] << endl;
    }
    int i = citations.size() - 1;
    for(; i >= 0; i--){
        cout << citations[i] << ", " << citations.size() - i << endl;
        if(citations[i] >= citations.size() - i){
            continue;
        }else{
            break;
        }
    }
    return citations.size() - i - 1;
}



int main()
{
    vector<int> citations = {3, 0, 6, 1, 5};
    cout << hIndex(citations) << endl;;
    system("pause");
    return 0;
}