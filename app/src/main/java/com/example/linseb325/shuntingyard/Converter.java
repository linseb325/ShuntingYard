package com.example.linseb325.shuntingyard;

import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by linseb325 on 4/18/16.
 */
public class Converter
{
    public StringTokenizer inputTokenizer;
    private Stack<String> operatorStack;
    private Stack<String> outputStack;


    public Converter(String stringToTokenize)
    {
        String newString = stringToTokenize.replaceAll("\\s+", "");
        System.out.println(newString);
        this.inputTokenizer = new StringTokenizer(newString, "^*/+-()", true);
        this.operatorStack = new Stack<String>();
        this.outputStack = new Stack<String>();
    }

    public String convert()
    {
        String answerString = "";
        String currToken;

        while(this.inputTokenizer.hasMoreTokens()) // While there is still input to be read...
        {
            currToken = this.inputTokenizer.nextToken(); // Read in a token.

            if(isNumeric(currToken)) // If the current token is a number...
            {
                this.outputStack.push(currToken);
            }
            else if(isMathOperator(currToken)) // If the current token is an operator...
            {
                while((!this.operatorStack.isEmpty() && !isRightAssociative(currToken) && isMathOperator(this.operatorStack.peek()) && precedence(currToken) <= precedence(this.operatorStack.peek()))
                        ||
                        (!this.operatorStack.isEmpty() && isRightAssociative(currToken) && isMathOperator(this.operatorStack.peek()) && precedence(currToken) < precedence(this.operatorStack.peek())))
                {
                    this.outputStack.push(this.operatorStack.pop());
                }
                this.operatorStack.push(currToken);
            }
            else if(currToken.equals("(")) // If the current token is an opening parenthesis...
            {
                this.operatorStack.push(currToken);
            }
            else if(currToken.equals(")")) // If the current token is a closing parenthesis...
            {
                while(!this.operatorStack.isEmpty() && !this.operatorStack.peek().equals("("))
                {
                    this.outputStack.push(this.operatorStack.pop());
                }
                if(this.operatorStack.isEmpty())
                {
                    System.out.println("isempty");
                }
                if(this.operatorStack.peek() == "(")
                {
                    System.out.println("Top of op stack is (");
                }
                this.operatorStack.pop();
            }
        }
        while(!this.operatorStack.isEmpty())
        {
            this.outputStack.push(this.operatorStack.pop());
        }
        while(!this.outputStack.isEmpty()) // Put the contents of the stack into a string in reverse order.
        {
            answerString = this.outputStack.pop() + answerString;
        }
        return answerString;
    }

    public int precedence(String operator)
    {
        if(operator.equals("^"))
        {
            return 4;
        }
        else if(operator.equals("*") || operator.equals("/"))
        {
            return 3;
        }
        else // It must be a "+" or "-"
        {
            return 2;
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
        if(str.equals("^"))
        {
            return true;
        }
        else return false;
    }

}
