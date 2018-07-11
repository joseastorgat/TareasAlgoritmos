# coding=utf-8
from math import factorial


def palindrome(palabra=""):

	dic = contar_letras(palabra)
	impar = 0

	n = 0
	d = 1

	for letra, cantidad in dic.iteritems():
		if cantidad !=0:
			if cantidad%2 != 0:
				if impar==0:
					impar=1
					cantidad-=1
				else:
					print("debug 0")
					return 0
			n+= cantidad/2
			if cantidad/2>1:
				d *= factorial(cantidad/2)
		else:
			pass

	P = factorial(n)/d 
	return P



def contar_letras(palabra=""):
	letras = "abcdefghijklmnopqrstuvwxyz"
	dic = {}
	for letra in letras:
		dic[letra] = 0

	for letra in palabra.lower():
		dic[letra]+=1
	return dic


def main():
	print(palindrome(raw_input("Ingrese Palabra \n")))

if __name__ == '__main__':
	main()