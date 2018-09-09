/*
 * Copyright 2018 The Chromium Authors. All rights reserved.
 * Use of this source code is governed by a BSD-style license that can be
 * found in the LICENSE file.
 */
package io.flutter.logging;

import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

public class FlutterLogEntry {

  public static final FlutterLog.Level UNDEFINED_LEVEL = FlutterLog.Level.INFO;
  /**
   * Pattern for stacktrace log.
   * Example: I/flutter (14706): #0      _MyHomePageState._incrementCounter (file:///Users/sondq/IdeaProjects/test_flutter/lib/main.dart:52:7)
   */
  private static final Pattern STACKTRACE_PATTERN = Pattern.compile("(?s).*\\s+#\\d+\\s+.*");

  private final long timestamp;
  @NotNull
  private final String category;
  private final int level;
  @NotNull
  private final String message;
  private int sequenceNumber = -1;

  public FlutterLogEntry(long timestamp, @NotNull String category, int level, @Nullable String message) {
    this.timestamp = timestamp;
    this.category = category;
    this.level = level;
    this.message = StringUtil.notNullize(message);
  }

  public FlutterLogEntry(long timestamp, @NotNull String category, @Nullable String message) {
    this(timestamp, category, UNDEFINED_LEVEL.value, message);
  }

  public long getTimestamp() {
    return timestamp;
  }

  public int getLevel() {
    return level;
  }

  @NotNull
  public String getLevelName() {
    return FlutterLog.Level.forValue(level).toDisplayString();
  }

  @NotNull
  public String getCategory() {
    return category;
  }

  @NotNull
  public String getMessage() {
    return message;
  }

  public boolean isStacktrace() {
    // TODO: Improve this, since it is a temporary trick.
    return STACKTRACE_PATTERN.matcher(message).matches();
  }

  /**
   * Return a sequence number, or -1 if unset.
   */
  public int getSequenceNumber() {
    return sequenceNumber;
  }

  public void setSequenceNumber(int sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }
}
