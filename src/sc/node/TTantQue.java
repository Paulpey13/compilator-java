/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class TTantQue extends Token
{
    public TTantQue()
    {
        super.setText("tantque");
    }

    public TTantQue(int line, int pos)
    {
        super.setText("tantque");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TTantQue(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTTantQue(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TTantQue text.");
    }
}
