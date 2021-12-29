#include "../LeetcodeHeader.hpp"

int convertInteger(int, int);

int main(){
    cout << convertInteger(29, 15);
    return 0;
}

int convertInteger(int A, int B){
    int binary = A ^ B;
    int count = 0;
    for(int i = 0; i < 32; i++){
        if(((binary >> i) & 1) == 1){
            count++;
        }
    }
    return count;
}
