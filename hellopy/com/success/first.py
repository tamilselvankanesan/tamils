'''
Created on Mar 10, 2020

@author: btamilselvan
'''
import string
print('''hello world testing ''')

# print('add two number: ')
# num1 = int(input('enter number 1: '))
# num2 = int(input('enter number 2: '))
# print('sum is %d' %(num1+num2))

print('\n Find the Square Root: ')

for i in range(17):
    print('Square root of %d is %d' %(i, i**.5))
    
# print("swap two numbers.. \n")
# num1 = int(input("enter number 1:"))
# num2 = int(input("enter number 2:"))
# num1,num2 = num2,num1
# print("after swapping num1 is {}, num2 is {}".format(num1, num2))

mList = [0, 1, 2,4]
print(mList.__dir__())
print('original list', mList)
mList.reverse()
print('reversed list', *mList)

mList = [range(1, 50, 10)]
print(mList)

mList = [i*2 for i in range(10)]
print(mList)

for i in range(10):
    mList.append(i)
print(mList)

mystr = 'hello'
mystr = ["{:03d}".format(i) for i in range(10)]
print(mystr)
resStr = '';
for i in mystr:
    resStr +=i
print(resStr)
resStr.join(mystr)
print(resStr)

