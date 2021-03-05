# JavaRunPython
This is a small tool to run a py file in your java code.
You can import the jar file to your java project to run the py file and receive the return value of the python file.

## How to use
You just need to give some arguments to use this tool.  
Such as:  

**env_url** : *python环境的地址，我这里使用的是annaconda的环境，所以需要改成annaconda对应环境下的python路径
          注意末尾不要加 .exe*  
**py_url** : *要运行的python文件路径*  
**excel_url** : *变量1(可选)*  
**args2** : *变量2(可选)*  
  
**env_url** : *The address of the python environment, I am using the annaconda environment here, so I need to change to the python path in the corresponding environment of annaconda
            Note that do not write ‘.exe’ in the end.*  
**py_url** : *The python file path to run*  
**excel_url** : *Variable 1 (optional)*  
**args2** : *Variable 2 (optional)*  
  
```
String env_url = "D:\\Learning\\Anaconda3\\envs\\Test\\python";""  
String py_url = "C:\\Users\\user\\Desktop\\CCF.py";  
String excel_url = "\"C:\\Users\\user\\Desktop\\train_model1.xlsx\"";  
String args2 = "2";  

//You can use some different run_x() method to run the python file, they differ only in the type of return value.  
 
Jap jap = new Jap(env_url, py_url, new String[]{excel_url, args2});  
jap.run_return_string();  
```
## The Python file writing requirements
To run the python file, you should write your python script by some rules.
1. You have to use 'sys.argv[x]' to recieve the arguments from this tool. And the 'x' represents the order of the arguments (beginning with 1).
2. In this version of the tool, your python file ***can not*** use the ***'input()'*** method, otherwise the program can not continue to run.


