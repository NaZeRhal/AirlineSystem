package max.rzhe.airlines.web;

public class ActionResult {
    private final String url;
    private final boolean redirect;

    public ActionResult(String url, boolean redirect) {
        this.url = url;
        this.redirect = redirect;
    }

    public ActionResult(String url) {
        this(url, true);
    }

    public String getUrl() {
        return url;
    }

    boolean isRedirect() {
        return redirect;
    }
}
