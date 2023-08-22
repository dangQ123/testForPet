package edu.njust.testforpet.util;


/**  自定义，平面二维向量类  **/
public class Vector2D implements Cloneable {
    private double x;
    private double y;

    //get
    public double getX() { return x;}
    public double getY() { return y;}

    //set
    public void setX(double x) { this.x = x;}
    public void setY(double y) { this.y = y;}

    @Override
    public String toString() {
        return "Vector2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /** 空构造 默认值V(x,y)=>V(0,0) **/
    public Vector2D()
    {
        x = 0;
        y = 0;
    }

    /** 赋值构造 V(x,y) **/
    public Vector2D(double _x, double _y)
    {
        x = _x;
        y = _y;
    }

    /**  获取弧度  **/
    public double getRadian()
    {
        return Math.atan2(y, x);
    }

    /**  获取角度  **/
    public double getAngle()
    {
        return getRadian() / Math.PI * 180;
    }

    /**  克隆该对象  **/
    @Override
    public Vector2D clone()
    {
        return new Vector2D(x,y);
    }

    public double getLength()
    {
        return Math.sqrt(getLengthSQ());
    }

    public double getLengthSQ()
    {
        return x * x + y * y;
    }

    /**  向量置零  **/
    public Vector2D Zero()
    {
        x = 0;
        y = 0;
        return this;
    }

    /** 该向量是否是置零的，是返回true，反之false **/
    public boolean isZero()
    {
        return x == 0 && y == 0;
    }

    /** 向量的长度设置为我们期待的value  **/
    public void setLength(double value)
    {
        double _angle = getAngle();
        x = Math.cos(_angle) * value;
        y = Math.sin(_angle) * value;
    }

    /**  向量的标准化（方向不变，长度为1）  **/
    public Vector2D normalize()
    {
        double length = getLength();
        x = x / length;
        y = y / length;
        return this;
    }

    /**  是否已经标准化  **/
    public boolean isNormalized()
    {
        return getLength() == 1.0;
    }

    /**  向量的方向翻转  **/
    public Vector2D reverse()
    {
        x = -x;
        y = -y;
        return this;
    }

    /**  2个向量的数量积(点积)  **/
    public double dotProduct(Vector2D v)
    {
        return x * v.x + y * v.y;
    }

    /**  2个向量的向量积(叉积)  **/
    public double crossProduct(Vector2D v)
    {
        return x * v.y - y * v.x;
    }

    /**  计算2个向量的夹角弧度  **/
    /**  参考点积公式:v1 * v2 = cos<v1,v2> * |v1| *|v2|  **/
    public static double radianBetween(Vector2D v1, Vector2D v2)
    {
        if(!v1.isNormalized()) {
            v1 = v1.clone().normalize(); // |v1| = 1
        }
        if(!v2.isNormalized()) {
            v2 = v2.clone().normalize(); // |v2| = 1
        }
        return Math.acos(v1.dotProduct(v2));
    }

    //弧度 = 角度乘以PI后再除以180、 推理可得弧度换算角度的公式
    /**  弧度转角度  **/
    public static double radian2Angle(double radian)
    {
        return radian / Math.PI * 180;
    }

    /** 向量加 **/
    public Vector2D add(Vector2D v)
    {
        return new Vector2D(x + v.x, y + v.y);
    }

    /** 向量减 **/
    public Vector2D subtract(Vector2D v)
    {
        return new Vector2D(x - v.x, y - v.y);
    }

    /** 向量乘 **/
    public Vector2D multiply(double value)
    {
        return new Vector2D(x * value, y * value);
    }

    /** 向量除 **/
    public Vector2D divide(double value)
    {
        return new Vector2D(x / value, y / value);
    }
}

