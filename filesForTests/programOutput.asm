.MODEL	SMALL
.STACK	256
.DATA
a	DW	?
b	DW	41
c	DW	?
.CODE
	
program:
MOV	AX,	@data
MOV	DS,	AX
	
MOV	AX,	4C00h
INT	21h
END	program
