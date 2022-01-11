#include "../LeetcodeHeader.hpp"

bool isAdditiveNumber(string num);
bool perfecrAdd(string a1, string a2, string a3);
bool recursion(string num, int i);

int main()
{
    cout << recursion("211738", -1) << endl;;
    system("pause");
    return 0;
}

bool recursion(string num, int i)
{
    int length = num.length();
    int i_end = length;
    if(i != -1){
        i_end = i + 1;//如果限制了第一个数，那么第一层循环没有必要执行多次了
    }else{
        i = 0;
    }
    for(; i < i_end; i++){
        string num1 = num.substr(0, i + 1);//第一个加数
        for(int j = i + 1; j < length; j++){
            if((length - j) < i || (length - j) < j - i){//剩下的数字太短了
                break;
            }
            string num2 = num.substr(i + 1, j - i);//第二个加数
            int digit = num1.length() > num2.length() ? num1.length() : num2.length();//最长的一个加数的位数
            string sum1 = num.substr(j + 1, digit);
            string sum2 = num.substr(j + 1, digit + 1);
            cout << "Checking:" << num1 << " + " << num2 << " = " << sum1 << endl;
            if(perfecrAdd(num1, num2, sum1)){
                if(num1.length() + num2.length() + sum1.length() == length){//数组遍历搜索结束
                    return true;
                }
                if(recursion(num.substr(i + 1, length - 1), num2.length() - 1)){
                    return true;
                }
            }
            cout << "Checking:" << num1 << " + " << num2 << " = " << sum2 << endl;
            if(perfecrAdd(num1, num2, sum2)){
                if(num1.length() + num2.length() + sum2.length() == length){//数组遍历搜索结束
                    return true;
                }
                if(recursion(num.substr(i + 1, length - 1), num2.length() - 1)){
                    return true;
                }
            }
        }
    }
    return false;
}

bool perfecrAdd(string a1, string a2, string a3)
{
    if((a1.length() > 1 && a1[0] == '0') || (a2.length() > 1 && a2[0] == '0') || (a3.length() > 1 && a3[0] == '0')){
        return false;
    }
    int b1 = a1.length() - 1;
    int b2 = a2.length() - 1;
    int b3 = a3.length() - 1;
    if (b1 > b3 || b2 > b3)
    { //加数太大的情况，如果某个加数位数比和还高，那么一定错误
        return false;
    }
    bool flag = false;
    while (b1 >= 0 || b2 >= 0 || b3 >= 0)
    {
        if (b1 < 0 && b2 < 0 && b3 >= 0 && (!flag))
        { //加数太小的情况，两个加数都已用尽
            return false;
        }
        char c1 = b1 >= 0 ? a1[b1] : '0'; //如果加数1用尽，本位设0
        char c2 = b2 >= 0 ? a2[b2] : '0'; //如果加数2用尽，本位设0
        char c3 = a3[b3];
        int c4 = (c1 - '0') + (c2 - '0') + (flag ? 1 : 0); //求和
        if (c4 % 10 != (c3 - '0'))
        { //本位是否相同
            return false;
        }
        if (c4 >= 10)
        { //进位情况
            flag = true;
        }
        else
        {
            flag = false;
        }
        b1--;
        b2--;
        b3--;
    }
    return true;
}