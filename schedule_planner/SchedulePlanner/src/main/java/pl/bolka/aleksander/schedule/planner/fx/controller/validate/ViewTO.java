package pl.bolka.aleksander.schedule.planner.fx.controller.validate;

/**
 * Created by Aleksander on 2017-01-04.
 */
public abstract class ViewTO {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewTO viewTO = (ViewTO) o;

        return id != null ? id.equals(viewTO.id) : viewTO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
