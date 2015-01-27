package com.gmail.bogatyr.alexander.shared.i18n;

public interface AppMessages extends com.google.gwt.i18n.client.Messages {

    @Key("list.widget.label.title")
    String listWidgetLabelTitle();

    @Key("list.widget.label.search")
    String listWidgetLabelSearch();

    @Key("list.widget.button.search")
    String listWidgetButtonSearch();

    @Key("list.widget.label.number")
    String listWidgetLabelNumber();

    @Key("list.widget.label.name")
    String listWidgetLabelName();

    @Key("list.widget.button.add")
    String listWidgetButtonAdd();

    @Key("display.widget.label.title")
    String displayWidgetLabelTitle(String p0);

    @Key("display.widget.label.number")
    String displayWidgetLabelNumber();

    @Key("display.widget.label.name")
    String displayWidgetLabelName();

    @Key("display.widget.button.edit")
    String displayWidgetButtonEdit();

    @Key("display.widget.button.delete")
    String displayWidgetButtonDelete();

    @Key("display.widget.button.list")
    String displayWidgetButtonBack();

    @Key("edit.widget.label.title")
    String editWidgetLabelTitle(String p0);

    @Key("edit.widget.label.number")
    String editWidgetLabelNumber();

    @Key("edit.widget.label.name")
    String editWidgetLabelName();

    @Key("edit.widget.button.save")
    String editWidgetButtonSave();

    @Key("edit.widget.button.cancel")
    String editWidgetButtonCancel();

    @Key("common.button.delete")
    String commonButtonDelete();

    @Key("common.delete.entry.confirmation.message")
    String commonDeleteEntryConfirmationMessage();

    @Key("common.server.error")
    String commonServerError();

    @Key("javax.validation.constraints.NotNull.message")
    String javaxValidationConstraintsNotNullMessage();

    @Key("javax.validation.constraints.Size.message")
    String javaxValidationConstraintsSizeMessage();
}
