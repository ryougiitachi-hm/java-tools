// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: src/main/resources/protobuf/IqiyiDanmuku.proto

package per.itachi.java.tools.danmuku.infra.protobuf.iqiyi;

public final class IqiyiDanmuku {
  private IqiyiDanmuku() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_per_itachi_java_tools_danmuku_BulletInfo_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_per_itachi_java_tools_danmuku_BulletInfo_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_per_itachi_java_tools_danmuku_Entry_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_per_itachi_java_tools_danmuku_Entry_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_per_itachi_java_tools_danmuku_Danmuku_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_per_itachi_java_tools_danmuku_Danmuku_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n.src/main/resources/protobuf/IqiyiDanmu" +
      "ku.proto\022\035per.itachi.java.tools.danmuku\"" +
      "\035\n\nBulletInfo\022\017\n\007content\030\002 \001(\t\"F\n\005Entry\022" +
      "=\n\nbulletInfo\030\002 \003(\0132).per.itachi.java.to" +
      "ols.danmuku.BulletInfo\">\n\007Danmuku\0223\n\005ent" +
      "ry\030\006 \003(\0132$.per.itachi.java.tools.danmuku" +
      ".EntryB6\n2per.itachi.java.tools.danmuku." +
      "infra.protobuf.iqiyiP\001"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_per_itachi_java_tools_danmuku_BulletInfo_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_per_itachi_java_tools_danmuku_BulletInfo_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_per_itachi_java_tools_danmuku_BulletInfo_descriptor,
        new java.lang.String[] { "Content", });
    internal_static_per_itachi_java_tools_danmuku_Entry_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_per_itachi_java_tools_danmuku_Entry_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_per_itachi_java_tools_danmuku_Entry_descriptor,
        new java.lang.String[] { "BulletInfo", });
    internal_static_per_itachi_java_tools_danmuku_Danmuku_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_per_itachi_java_tools_danmuku_Danmuku_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_per_itachi_java_tools_danmuku_Danmuku_descriptor,
        new java.lang.String[] { "Entry", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}