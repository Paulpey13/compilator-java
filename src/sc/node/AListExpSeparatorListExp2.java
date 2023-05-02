/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class AListExpSeparatorListExp2 extends PListExp2
{
    private TComma _comma_;
    private PExp _exp_;
    private PListExp2 _listExp2_;

    public AListExpSeparatorListExp2()
    {
        // Constructor
    }

    public AListExpSeparatorListExp2(
        @SuppressWarnings("hiding") TComma _comma_,
        @SuppressWarnings("hiding") PExp _exp_,
        @SuppressWarnings("hiding") PListExp2 _listExp2_)
    {
        // Constructor
        setComma(_comma_);

        setExp(_exp_);

        setListExp2(_listExp2_);

    }

    @Override
    public Object clone()
    {
        return new AListExpSeparatorListExp2(
            cloneNode(this._comma_),
            cloneNode(this._exp_),
            cloneNode(this._listExp2_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAListExpSeparatorListExp2(this);
    }

    public TComma getComma()
    {
        return this._comma_;
    }

    public void setComma(TComma node)
    {
        if(this._comma_ != null)
        {
            this._comma_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._comma_ = node;
    }

    public PExp getExp()
    {
        return this._exp_;
    }

    public void setExp(PExp node)
    {
        if(this._exp_ != null)
        {
            this._exp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exp_ = node;
    }

    public PListExp2 getListExp2()
    {
        return this._listExp2_;
    }

    public void setListExp2(PListExp2 node)
    {
        if(this._listExp2_ != null)
        {
            this._listExp2_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._listExp2_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._comma_)
            + toString(this._exp_)
            + toString(this._listExp2_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._comma_ == child)
        {
            this._comma_ = null;
            return;
        }

        if(this._exp_ == child)
        {
            this._exp_ = null;
            return;
        }

        if(this._listExp2_ == child)
        {
            this._listExp2_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._comma_ == oldChild)
        {
            setComma((TComma) newChild);
            return;
        }

        if(this._exp_ == oldChild)
        {
            setExp((PExp) newChild);
            return;
        }

        if(this._listExp2_ == oldChild)
        {
            setListExp2((PListExp2) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
