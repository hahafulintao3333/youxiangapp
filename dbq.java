'''
from selenium import webdriver
import time
import xlrd

driver = webdriver.Chrome()
# 窗口最大化
driver.maximize_window()
#读取文件
data = xlrd.open_workbook("D:\\pc\\data\\test01.xls")
#读取第一个工作表
table = data.sheet_by_name('Sheet1')
#使用for循环输出所有数据
list=[]
for i in range (0,table.nrows):
    # 只读取其中一行
    list = table.row_values(i)
    break
print(list)
#判断是否有网址需要打开
if(str(list[0])!=''):
    driver.get(list[0])
#判断执行关键字
if(str(list[2])==u'输入'):
    if(list[1]=='id'):
        driver.find_element_by_id(list[3]).send_keys(list[4])
time.sleep(3)
if(str(list[6])==u'点击'):
    if(list[5]=='id'):
        driver.find_element_by_id(list[7]).click()
time.sleep(3)
driver.close()
'''
'''
from selenium import webdriver
import time
import xlrd

driver = webdriver.Chrome()
# 窗口最大化
driver.maximize_window()
#读取文件
data = xlrd.open_workbook("D:\\pc\\data\\test01.xls")
#读取第一个工作表
table = data.sheet_by_name('Sheet1')
#使用for循环输出所有数据
list=[]
for i in range (0,table.nrows):
    # 读取excel第一行
    list = table.row_values(i)
    break
print(list)
time.sleep(3)
#通过索引在excel表中获取百度URL
driver.get(list[0])
time.sleep(3)
#通过索引在excel表中获取百度输入框元素并输入要搜索的字段
driver.find_element_by_id(list[3]).send_keys(list[4])
time.sleep(3)
#通过索引在excel表中获取百度点击“百度一下”元素并点击
driver.find_element_by_id(list[7]).click()
time.sleep(3)
list=[]
for i in range (1,table.nrows):
    # 读取excel第二行
    list = table.row_values(i)
    break
print(list)
#跳转到“百度首页”
driver.find_element_by_css_selector(list[0]).click()
time.sleep(3)
driver.close()
'''
'''
from selenium import webdriver
import time
driver = webdriver.Chrome()
driver.maximize_window()
driver.get('https://www.baidu.com')
class BasePage:
    def __int__(self):
        self.driver = webdriver.Chrome
        self.driver.get('https://www.baidu.com')
    def find_ele(self,*args):
        ele = self.driver.find_element(*args)
        return ele
    time.sleep(3)
driver.quit()
'''
'''
import requests
import json

url = 'http://localhost:8080/EasyBuy/Login'

data = 'loginName=admin&password=123456&action=login'
#使用data来进行传递
headers = {
     'Content-Type': 'application/x-www-from-urlencoded'
}

#response = requests.request('POST',url,headers=headers,params=data)
response = requests.post(url,headers=headers,params=data)

#print(response.text.encode('utf8'))
#print(response.json())
print(json.dumps(response.json(),indent=4, ensure_ascii=False))
'''
'''
import requests
import json
import jsonpath

url = "http://localhost:8000/login"
# python 字典 --》 json
data = {
    "username": "admin",
    "password": "admin"
}
res = requests.post(url=url, json=data)
#使用json包,打印json格式换行并且前面空四个空格，通过ascii防止乱码
print(json.dumps(res.json(), indent=4, ensure_ascii=False))
# 返回值是个列表 所以要加索引
# print(jsonpath.jsonpath(res.json(), "$..token")[0])

print("*************************************************************")

urllogin = "http://localhost:8000/auth/hello"
#使用jsonpath来提取
token = "Bearer " + jsonpath.jsonpath(res.json(), "$..token")[0]
headers = {
    "Authorization":token
}
res1 = requests.get(url=urllogin,headers=headers)
print(json.dumps(res1.json(), indent=4, ensure_ascii=False))

print('************************************************************')

url = "http://httpbin.org/post"
# 准备一个文件
file = open("D:\pc\data\学生消课","rb")
# 参数
files = {
    "file": file
}
res2 = requests.post(url=url, files=files)
#print(res.json())
print(json.dumps(res2.json(), indent=4, ensure_ascii=False))
'''

'''
import requests
import unittest
import HTMLTestRunnerCN


class People(unittest.TestCase):
    def test_login(self):
        user={"username":"admin","password":"admin"}
        r1=requests.post(url="http://localhost:9090/rlzy/logon.do?action=logon",params=user)
        print(r1.text)
    def test_add(self):
        adduser = {"username": "haoren", "password": "11111111",
                   "sex": "1", "birthday": "1993-3-3", "isadminhelp": "on",
                   "isadmin": "1", "content": "1"
                   }
        r2=requests.post(url="http://localhost:9090/rlzy/modifyuser.do?action=adduser",params=adduser)
        print(r2.text)

testunit=unittest.TestSuite()
testunit.addTest(People("test_login"))
testunit.addTest(People("test_add"))
filename = 'D:\\pc\\human_resources_report.html'
fp=open(filename,'wb')
runner = HTMLTestRunnerCN.HTMLTestRunner(
stream=fp,
title='接口测试报告',
description='用例执行情况：')
runner.run(testunit)
fp.close()
'''
'''
import requests
import xlrd
#读取文件
data = xlrd.open_workbook("D:\\pc\\data\\testcase02.xls")
#读取第一个工作表
table = data.sheet_by_name('Sheet1')
#使用for循环输出所有数据
list=[]
for i in range (1,table.nrows):
    # 读取excel表第二行数据
    list = table.row_values(i)
    break
print(list)

url = list[0]
data = list[1]

res = requests.get(url=url, params=data)

print(res.json())
'''
'''
import requests
import xlrd
import json
#读取文件
data = xlrd.open_workbook("D:\\pc\\data\\testcase02.xls")
#读取第一个工作表
table = data.sheet_by_name('Sheet1')
#使用for循环输出所有数据
list=[]
for i in range (0,table.nrows):
    # 读取excel表第一行数据
    list = table.row_values(i)
    break

# 打印第一行
#print(list)

# 获取URL接口地址
url=list[0]
# 获取接口参数json字符串，并把字符串转成python dict
data=json.loads(list[1])
#print(url,data)
# 提交POST请求
res = requests.post(url=url, json=data)
# 读取返回的JSON
print(res.json())
'''

import requests
import xlrd
import json
#读取文件
data = xlrd.open_workbook("D:\\pc\\data\\testcase02.xls")
#读取第一个工作表
table = data.sheet_by_name('Sheet1')
#使用for循环输出所有数据
list=[]
for i in range (2,table.nrows):
    # 读取excel表第二行数据
    list = table.row_values(i)
    break
print(list)

url = list[0]
data = list[1]

res = requests.get(url=url, params=data)

print(res.text)