package by.it.yatsevich.jd01_08;

import java.util.Arrays;

class Vector extends Var {

    public double[] getValue() {
        return value;
    }

    private double[] value;

    @Override
    public Var add(Var other) {
        if (other instanceof Vector) {
            double[] sum = Arrays.copyOf(value, value.length);
            for (int i = 0; i < value.length; i++) {
                sum[i] = sum[i] + ((Vector) other).value[i];
            }
            return new Vector(sum);
        } else if (other instanceof Scalar) {
            double[] sum = Arrays.copyOf(value, value.length);
            for (int i = 0; i < value.length; i++) {
                sum[i] += ((Scalar) other).getValue();
            }
            return new Vector(sum);
        } else
            return super.add(other);
    }


    @Override
    public Var sub(Var other) {
        if (other instanceof Vector) {
            double[] sub = Arrays.copyOf(value, value.length);
            for (int i = 0; i < value.length; i++) {
                sub[i] = this.value[i] - ((Vector) other).value[i];
            }
            return new Vector(sub);
        } else if (other instanceof Scalar) {
            double[] sub = Arrays.copyOf(value, value.length);
            for (int i = 0; i < value.length; i++) {
                sub[i] = sub[i] - ((Scalar) other).getValue();
            }
            return new Vector(sub);
        } else
            return new Scalar(-1).mul(other).add(this);
    }

    @Override
    public Var mul(Var other) {
        if (other instanceof Vector) {
            double mul = 0;
            for (int i = 0; i < value.length; i++) {
                mul = (this.value[i] * ((Vector) other).value[i]) + mul;
            }
            return new Scalar(mul);
        } else if (other instanceof Scalar) {
            double[] mul = Arrays.copyOf(value, value.length);
            for (int i = 0; i < value.length; i++) {
                mul[i] = mul[i] * ((Scalar) other).getValue();
            }
            return new Vector(mul);
        } else
            return super.mul(other);
    }

    @Override
    public Var div(Var other) {
        if (other instanceof Vector) {
            return super.div(other);
        } else if (other instanceof Scalar && ((Scalar) other).getValue() != 0) {
            double[] div = Arrays.copyOf(value, value.length);
            for (int i = 0; i < value.length; i++) {
                div[i] = div[i] / ((Scalar) other).getValue();
            }
            return new Vector(div);
        } else
            return super.div(other);
    }

    Vector(double[] value) {
        this.value = Arrays.copyOf(value, value.length);
    }

    Vector(Vector vector) {
        this.value = Arrays.copyOf(vector.value, vector.value.length);
    }

    Vector(String strVector) {
        String[] string = strVector
                .replace('{', ' ')
                .replace('}', ' ')
                .trim()
                .split(",\\s*");
        double[] array = new double[string.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Double.parseDouble(string[i]);
        }
        this.value = array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        String delimiter = "";
        for (double element : value) {
            sb.append(delimiter).append(element);
            delimiter = ", ";
        }
        sb.append("}");
        return sb.toString();
    }
}
