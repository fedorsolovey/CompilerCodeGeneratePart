b	DW	?
c	DW	?
	
program:
MOV	c,	15
MOV	AX,	cA
PUSH	AX
MOV	AX,	c
PUSH	AX
POP	BX
POP	AX
IDIV	AX,	BX
MOV	b,	AX
	
MOV	AX,	4C00h
INT	21h
END	program
