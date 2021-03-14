public class Student {
    String id, fn, ln, prog, level, asu;
    String[] date;
    int[] time;

    public String getId() {
        return id;
    }

    public String getFn() {
        return fn;
    }

    public String getLn() {
        return ln;
    }

    public String getProg() {
        return prog;
    }

    public String getLevel() {
        return level;
    }

    public String getAsu() {
        return asu;
    }

    public String[] getDate() {
        return date;
    }

    public int[] getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public void setProg(String prog) {
        this.prog = prog;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setAsu(String asu) {
        this.asu = asu;
    }

    public void setDate(String[] date) {
        this.date = date;
    }

    public void setTime(int[] time) {
        this.time = time;
    }
}

