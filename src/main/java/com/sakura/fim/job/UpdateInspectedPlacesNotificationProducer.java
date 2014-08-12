package com.sakura.fim.job;

import java.time.format.DateTimeFormatter;

import com.sakura.fim.util.notifier.NotificationProducer;
import com.sakura.fim.util.notifier.NotifierType;

public class UpdateInspectedPlacesNotificationProducer implements NotificationProducer {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private UpdateInspectedPlacesTaskResult result;

    public UpdateInspectedPlacesNotificationProducer(UpdateInspectedPlacesTaskResult result) {
        this.result = result;
    }

    @Override
    public String getSubject() {
        return String.format("Results for the %s UpdatedInspectedPlaces task.", result.getExecutionTime().format(DATE_FORMATTER));
    }

    @Override
    public String getBodyMessage() {
        StringBuilder builder = new StringBuilder();
        builder.append("<div><h2><strong>Execution time</strong></h2>")
                .append(String.format("<p>%s</p></div><br/>", result.getExecutionTime().format(DATE_FORMATTER)))
                .append("<div><h2><strong>Retrieved inspections</strong></h2>")
                .append(String.format("<p>%s</p></div><br/>", result.getRetrievedInspectionsCount()))
                .append("<div><h2><strong>Geocoding result</strong></h2>");
        result.getGeocodingsResult().forEach((key, value) -> builder.append(String.format("<p>%s | %s</p><br/>", key, value)));
        return builder.append("</div><br/>").append("<div><h2><strong>Valid stored inspections count(geocoded)</strong></h2>")
                .append(String.format("<p>%s</p></div><br/>", result.getValidStoredInspectionsCount()))
                .append("<div><h2><strong>Current version was</strong></h2>")
                .append(String.format("<p>%s</p></div><br/>", result.getCurrentVersion()))
                .append("<div><h2><strong>New collection version stored</strong></h2>")
                .append(String.format("<p>%s</p></div><br/>", result.getStoredVersion() > 0 ? result.getStoredVersion() : "No valid version stored"))
                .toString();
    }

    @Override
    public NotifierType getNotifierType() {
        return NotifierType.EMAIL_HTML;
    }
}
