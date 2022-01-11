#include "../LeetcodeHeader.hpp"

float sum(vector<float> v){
    float res = 0;
    for(float i : v){
        res += i;
    }
    return res;
}
bool check(vector<float> times, vector<float> times_rest, int hoursBefore){
    return sum(times) + sum(times_rest) <= hoursBefore;
}

int minSkips(vector<int> &dist, int speed, int hoursBefore)
{
    vector<float> times = vector<float>();
    vector<float> times_rest = vector<float>();
    for (auto i : dist)
    {
        float time = i / (float)speed;
        float time_rest = ceil(time) - time;
        cout << time << "     " << time_rest << endl;
        times.push_back(i / (float)speed);
        times_rest.push_back(time_rest);
    }
    times_rest.pop_back();
    if (sum(times) > hoursBefore)
    { //特殊情况1：跳过每个休息时间仍不能到达会议现场
        return -1;
    }
    if (sum(times) + sum(times_rest) < hoursBefore)
    { //特殊情况2：不需要跳过任何休息时间仍可以按时到达会议现场
        return 0;
    }
    int counter = 0;//删除休息时间的计数器
    while(!check(times, times_rest, hoursBefore)){
        counter++;
        cout << endl << "times" << endl;
        for(auto i : times){
            cout << i << endl;
        }
        cout << endl << "times_rest" << endl;
        for(auto i : times_rest){
            cout << i << endl;
        }
        float max_rest = times_rest[0];
        int max_time = 0;
        for(int i = 1; i < times_rest.size(); i++){
            if(times_rest[i] > max_rest){
                max_rest = times_rest[i];
                max_time = i;
            }
        }
        if(times[max_time + 1] <= max_rest){
            times[max_time] += times[max_time + 1];
            times_rest[max_time] = max_rest - times[max_time + 1];
            times[max_time + 1] = 0;
            times_rest[max_time + 1] = 0;
        }else {
            times[max_time] += max_rest;
            times[max_time + 1] -= max_rest;
            times_rest[max_time] = 0;
            times_rest[max_time + 1] += max_rest;
            while(times_rest[max_time + 1] >= 1){
                times_rest[max_time + 1]--;
            }
        }
    }
    return counter;
}
int main()
{
    vector<int> dist = {2, 1, 5, 4, 4, 3, 2, 9, 2, 10};
    int speed = 6;
    int hoursBefore = 7;
    cout << minSkips(dist, speed, hoursBefore) << endl;;
    system("pause");
    return 0;
}