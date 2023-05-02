/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AListDecVariableOptProgramme extends PProgramme
{
    private PListDecVariableOpt _listDecVariableOpt_;
    private PListDecFunction _listDecFunction_;

    public AListDecVariableOptProgramme()
    {
        // Constructor
    }

    public AListDecVariableOptProgramme(
        @SuppressWarnings("hiding") PListDecVariableOpt _listDecVariableOpt_,
        @SuppressWarnings("hiding") PListDecFunction _listDecFunction_)
    {
        // Constructor
        setListDecVariableOpt(_listDecVariableOpt_);

        setListDecFunction(_listDecFunction_);

    }

    @Override
    public Object clone()
    {
        return new AListDecVariableOptProgramme(
            cloneNode(this._listDecVariableOpt_),
            cloneNode(this._listDecFunction_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAListDecVariableOptProgramme(this);
    }

    public PListDecVariableOpt getListDecVariableOpt()
    {
        return this._listDecVariableOpt_;
    }

    public void setListDecVariableOpt(PListDecVariableOpt node)
    {
        if(this._listDecVariableOpt_ != null)
        {
            this._listDecVariableOpt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listDecVariableOpt_ = node;
    }

    public PListDecFunction getListDecFunction()
    {
        return this._listDecFunction_;
    }

    public void setListDecFunction(PListDecFunction node)
    {
        if(this._listDecFunction_ != null)
        {
            this._listDecFunction_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listDecFunction_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._listDecVariableOpt_)
            + toString(this._listDecFunction_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._listDecVariableOpt_ == child)
        {
            this._listDecVariableOpt_ = null;
            return;
        }

        if(this._listDecFunction_ == child)
        {
            this._listDecFunction_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._listDecVariableOpt_ == oldChild)
        {
            setListDecVariableOpt((PListDecVariableOpt) newChild);
            return;
        }

        if(this._listDecFunction_ == oldChild)
        {
            setListDecFunction((PListDecFunction) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
