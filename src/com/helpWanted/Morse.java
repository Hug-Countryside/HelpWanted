package com.helpWanted;

public class Morse {
	String m_MorseCode;
	String m_MorseString;
	MorseInfo mi;

	public Morse() {
		this.m_MorseCode = "";
		this.m_MorseString = "";
		this.mi = new MorseInfo();
	}

	public Morse(String str) {
		this.m_MorseCode = "";
		this.m_MorseString = "";
		this.mi = new MorseInfo();
		this.m_MorseString = str;
	}

	public String getMorseCode() {
		this.doWork();
		return m_MorseCode;
	}

	public void setMorseString(String str) {
		this.m_MorseString = str;
	}

	private void doWork() {
		for (int i = 0; i < this.m_MorseString.length(); i++) {
			doMorseChar(this.m_MorseString.charAt(i));
		}
	}

	private void doMorseChar(char morseChar) {
		if (morseChar == 'A' || morseChar == 'a') {
			this.m_MorseCode += mi.a;
		} else if (morseChar == 'B' || morseChar == 'b') {
			this.m_MorseCode += mi.b;
		} else if (morseChar == 'C' || morseChar == 'c') {
			this.m_MorseCode += mi.c;
		} else if (morseChar == 'D' || morseChar == 'd') {
			this.m_MorseCode += mi.d;
		} else if (morseChar == 'E' || morseChar == 'e') {
			this.m_MorseCode += mi.e;
		} else if (morseChar == 'F' || morseChar == 'f') {
			this.m_MorseCode += mi.f;
		} else if (morseChar == 'G' || morseChar == 'g') {
			this.m_MorseCode += mi.g;
		} else if (morseChar == 'H' || morseChar == 'h') {
			this.m_MorseCode += mi.h;
		} else if (morseChar == 'I' || morseChar == 'i') {
			this.m_MorseCode += mi.i;
		} else if (morseChar == 'J' || morseChar == 'j') {
			this.m_MorseCode += mi.j;
		} else if (morseChar == 'K' || morseChar == 'k') {
			this.m_MorseCode += mi.k;
		} else if (morseChar == 'L' || morseChar == 'l') {
			this.m_MorseCode += mi.l;
		} else if (morseChar == 'M' || morseChar == 'm') {
			this.m_MorseCode += mi.m;
		} else if (morseChar == 'N' || morseChar == 'n') {
			this.m_MorseCode += mi.n;
		} else if (morseChar == 'O' || morseChar == 'o') {
			this.m_MorseCode += mi.o;
		} else if (morseChar == 'P' || morseChar == 'p') {
			this.m_MorseCode += mi.p;
		} else if (morseChar == 'Q' || morseChar == 'q') {
			this.m_MorseCode += mi.q;
		} else if (morseChar == 'R' || morseChar == 'r') {
			this.m_MorseCode += mi.r;
		} else if (morseChar == 'S' || morseChar == 's') {
			this.m_MorseCode += mi.s;
		} else if (morseChar == 'T' || morseChar == 't') {
			this.m_MorseCode += mi.t;
		} else if (morseChar == 'U' || morseChar == 'u') {
			this.m_MorseCode += mi.u;
		} else if (morseChar == 'V' || morseChar == 'v') {
			this.m_MorseCode += mi.v;
		} else if (morseChar == 'W' || morseChar == 'w') {
			this.m_MorseCode += mi.w;
		} else if (morseChar == 'X' || morseChar == 'x') {
			this.m_MorseCode += mi.x;
		} else if (morseChar == 'Y' || morseChar == 'y') {
			this.m_MorseCode += mi.y;
		} else if (morseChar == 'Z' || morseChar == 'z') {
			this.m_MorseCode += mi.z;
		} else if (morseChar == '0') {
			this.m_MorseCode += mi.N0;
		} else if (morseChar == '1') {
			this.m_MorseCode += mi.N1;
		} else if (morseChar == '2') {
			this.m_MorseCode += mi.N2;
		} else if (morseChar == '3') {
			this.m_MorseCode += mi.N3;
		} else if (morseChar == '4') {
			this.m_MorseCode += mi.N4;
		} else if (morseChar == '5') {
			this.m_MorseCode += mi.N5;
		} else if (morseChar == '6') {
			this.m_MorseCode += mi.N6;
		} else if (morseChar == '7') {
			this.m_MorseCode += mi.N7;
		} else if (morseChar == '8') {
			this.m_MorseCode += mi.N8;
		} else if (morseChar == '9') {
			this.m_MorseCode += mi.N9;
		} else if (morseChar == ' ') {
			this.m_MorseCode += mi.space;
		}

	}
}
