package com.geib.invest.views.realestate;

import com.geib.invest.data.SamplePerson;
import com.geib.invest.services.SamplePersonService;
import com.geib.invest.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("Real Estate")
@Route(value = "realestate", layout = MainLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class RealEstateView extends Composite<VerticalLayout> {

    public RealEstateView() {
        TabSheet tabSheet = new TabSheet();
        Grid stripedGrid = new Grid(SamplePerson.class);
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, tabSheet);
        tabSheet.setWidth("100%");
        tabSheet.getStyle().set("flex-grow", "1");
        setTabSheetSampleData(tabSheet);
        stripedGrid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, stripedGrid);
        stripedGrid.setWidth("100%");
        stripedGrid.setHeight("100%");
        setGridSampleData(stripedGrid);
        getContent().add(tabSheet);
        getContent().add(stripedGrid);
    }

    private void setTabSheetSampleData(TabSheet tabSheet) {
        tabSheet.add("Dashboard", new Div(new Text("This is the Dashboard tab content")));
        tabSheet.add("Payment", new Div(new Text("This is the Payment tab content")));
        tabSheet.add("Shipping", new Div(new Text("This is the Shipping tab content")));
    }

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
    }

    @Autowired()
    private SamplePersonService samplePersonService;
}
