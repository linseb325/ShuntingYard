package com.example.linseb325.shuntingyard;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by linseb325 on 4/18/16.
 */
public class Converter
{
    public StringTokenizer inputTokenizer;
    private String inputString;
    private Stack<String> operatorStack;
    private Stack<String> outputStack;


    public Converter(String stringToTokenize)
    {
        this.inputTokenizer = new StringTokenizer(stringToTokenize, "^*/+-()", true);
        this.inputString = stringToTokenize;
    }

    public String convert()
    {
        String answerString = "";
        String currToken;

        while(this.inputTokenizer.hasMoreTokens()) // While there is still input to be read...
        {
            currToken = inputTokenizer.nextToken(); // Read in a token.

            if(isNumeric(currToken)) // If the current token is a number...
            {
                this.outputStack.push(currToken);
            }
            if(isMathOperator(currToken)) // If the current token is an operator...
            {
                while((operatorStack.peek() != null && (!isRightAssociative(currToken) && precedence(currToken) <= precedence(operatorStack.peek()))) || (operatorStack.peek() != null && isRightAssociative(currToken) && precedence(currToken) < precedence(operatorStack.peek())))
                {
                    outputStack.push(operatorStack.pop());
                }
                operatorStack.push(currToken);
            }
            if(currToken == "(") // If the current token is an opening parenthesis...
            {
                operatorStack.push(currToken);
            }
            if(currToken == ")") // If the current token is a closing parenthesis...
            {
                while(this.operatorStack.peek() != "(")
                {
                    this.outputStack.push(this.operatorStack.pop());
                }
                this.operatorStack.pop();
            }
        }
        while(!operatorStack.isEmpty())
        {
            outputStack.push(operatorStack.pop());
        }
        while(!outputStack.isEmpty()) // Put the contents of the stack into a string in reverse order.
        {
            answerString = outputStack.pop() + answerString;
        }
        return answerString;
    }

    public int precedence(String operator)
    {
        if(operator == "^")
        {
            return 4;
        }
        else if(operator == "*" || operator == "/")
        {
            return 3;
        }
        else if(operator == "+" || operator == "-")
        {
            return 2;
        }
        else
        {
            System.out.println("Invalid operator for precedence method!");
            return -1;
        }

    }

    public boolean isNumeric(String str)
    {
        for(char c : str.toCharArray())
        {
            if(!Character.isDigit(c)) return false;
        }
        return true;
    }

    public boolean isMathOperator(String str)
    {
        String operators = "^*/+-";
        for(char op: operators.toCharArray())
        {
            if(str.charAt(0) == op)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isRightAssociative(String str)
    {
        if(str == "^")
        {
            return true;
        }
        else return false;
    }

}
