/* This file was generated by SableCC (http://www.sablecc.org/). */

package sc.node;

import sc.analysis.*;

@SuppressWarnings("nls")
public final class ATableauDecVariable extends PDecVariable
{
    private PType _type_;
    private TId _id_;
    private TCrochetOuv _crochetOuv_;
    private TNumber _number_;
    private TCrochetFer _crochetFer_;

    public ATableauDecVariable()
    {
        // Constructor
    }

    public ATableauDecVariable(
        @SuppressWarnings("hiding") PType _type_,
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") TCrochetOuv _crochetOuv_,
        @SuppressWarnings("hiding") TNumber _number_,
        @SuppressWarnings("hiding") TCrochetFer _crochetFer_)
    {
        // Constructor
        setType(_type_);

        setId(_id_);

        setCrochetOuv(_crochetOuv_);

        setNumber(_number_);

        setCrochetFer(_crochetFer_);

    }

    @Override
    public Object clone()
    {
        return new ATableauDecVariable(
            cloneNode(this._type_),
            cloneNode(this._id_),
            cloneNode(this._crochetOuv_),
            cloneNode(this._number_),
            cloneNode(this._crochetFer_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseATableauDecVariable(this);
    }

    public PType getType()
    {
        return this._type_;
    }

    public void setType(PType node)
    {
        if(this._type_ != null)
        {
            this._type_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._type_ = node;
    }

    public TId getId()
    {
        return this._id_;
    }

    public void setId(TId node)
    {
        if(this._id_ != null)
        {
            this._id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._id_ = node;
    }

    public TCrochetOuv getCrochetOuv()
    {
        return this._crochetOuv_;
    }

    public void setCrochetOuv(TCrochetOuv node)
    {
        if(this._crochetOuv_ != null)
        {
            this._crochetOuv_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._crochetOuv_ = node;
    }

    public TNumber getNumber()
    {
        return this._number_;
    }

    public void setNumber(TNumber node)
    {
        if(this._number_ != null)
        {
            this._number_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._number_ = node;
    }

    public TCrochetFer getCrochetFer()
    {
        return this._crochetFer_;
    }

    public void setCrochetFer(TCrochetFer node)
    {
        if(this._crochetFer_ != null)
        {
            this._crochetFer_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._crochetFer_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._type_)
            + toString(this._id_)
            + toString(this._crochetOuv_)
            + toString(this._number_)
            + toString(this._crochetFer_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._type_ == child)
        {
            this._type_ = null;
            return;
        }

        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._crochetOuv_ == child)
        {
            this._crochetOuv_ = null;
            return;
        }

        if(this._number_ == child)
        {
            this._number_ = null;
            return;
        }

        if(this._crochetFer_ == child)
        {
            this._crochetFer_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._type_ == oldChild)
        {
            setType((PType) newChild);
            return;
        }

        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        if(this._crochetOuv_ == oldChild)
        {
            setCrochetOuv((TCrochetOuv) newChild);
            return;
        }

        if(this._number_ == oldChild)
        {
            setNumber((TNumber) newChild);
            return;
        }

        if(this._crochetFer_ == oldChild)
        {
            setCrochetFer((TCrochetFer) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
