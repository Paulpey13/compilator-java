/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AInstTantQueInstruction extends PInstruction
{
    private PInstTantQue _instTantQue_;

    public AInstTantQueInstruction()
    {
        // Constructor
    }

    public AInstTantQueInstruction(
        @SuppressWarnings("hiding") PInstTantQue _instTantQue_)
    {
        // Constructor
        setInstTantQue(_instTantQue_);

    }

    @Override
    public Object clone()
    {
        return new AInstTantQueInstruction(
            cloneNode(this._instTantQue_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInstTantQueInstruction(this);
    }

    public PInstTantQue getInstTantQue()
    {
        return this._instTantQue_;
    }

    public void setInstTantQue(PInstTantQue node)
    {
        if(this._instTantQue_ != null)
        {
            this._instTantQue_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._instTantQue_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._instTantQue_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._instTantQue_ == child)
        {
            this._instTantQue_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._instTantQue_ == oldChild)
        {
            setInstTantQue((PInstTantQue) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}