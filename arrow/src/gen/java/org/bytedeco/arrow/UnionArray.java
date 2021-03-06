// Targeted by JavaCPP version 1.5.3-SNAPSHOT: DO NOT EDIT THIS FILE

package org.bytedeco.arrow;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.arrow.global.arrow.*;


// ----------------------------------------------------------------------
// Union

/** Concrete Array class for union data */
@Namespace("arrow") @NoOffset @Properties(inherit = org.bytedeco.arrow.presets.arrow.class)
public class UnionArray extends Array {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public UnionArray(Pointer p) { super(p); }


  public UnionArray(@Const @SharedPtr @ByRef ArrayData data) { super((Pointer)null); allocate(data); }
  private native void allocate(@Const @SharedPtr @ByRef ArrayData data);

  
  ///
  ///
  public UnionArray(@SharedPtr @Cast({"", "std::shared_ptr<arrow::DataType>"}) DataType type, @Cast("int64_t") long length,
               @Const @ByRef ArrayVector children,
               @Const @SharedPtr @ByRef ArrowBuffer type_ids,
               @Const @SharedPtr @ByRef(nullValue = "std::shared_ptr<arrow::Buffer>(nullptr)") ArrowBuffer value_offsets,
               @Const @SharedPtr @ByRef(nullValue = "std::shared_ptr<arrow::Buffer>(nullptr)") ArrowBuffer null_bitmap,
               @Cast("int64_t") long null_count/*=arrow::kUnknownNullCount*/, @Cast("int64_t") long offset/*=0*/) { super((Pointer)null); allocate(type, length, children, type_ids, value_offsets, null_bitmap, null_count, offset); }
  private native void allocate(@SharedPtr @Cast({"", "std::shared_ptr<arrow::DataType>"}) DataType type, @Cast("int64_t") long length,
               @Const @ByRef ArrayVector children,
               @Const @SharedPtr @ByRef ArrowBuffer type_ids,
               @Const @SharedPtr @ByRef(nullValue = "std::shared_ptr<arrow::Buffer>(nullptr)") ArrowBuffer value_offsets,
               @Const @SharedPtr @ByRef(nullValue = "std::shared_ptr<arrow::Buffer>(nullptr)") ArrowBuffer null_bitmap,
               @Cast("int64_t") long null_count/*=arrow::kUnknownNullCount*/, @Cast("int64_t") long offset/*=0*/);
  public UnionArray(@SharedPtr @Cast({"", "std::shared_ptr<arrow::DataType>"}) DataType type, @Cast("int64_t") long length,
               @Const @ByRef ArrayVector children,
               @Const @SharedPtr @ByRef ArrowBuffer type_ids) { super((Pointer)null); allocate(type, length, children, type_ids); }
  private native void allocate(@SharedPtr @Cast({"", "std::shared_ptr<arrow::DataType>"}) DataType type, @Cast("int64_t") long length,
               @Const @ByRef ArrayVector children,
               @Const @SharedPtr @ByRef ArrowBuffer type_ids);

  /** \brief Construct Dense UnionArray from types_ids, value_offsets and children
   * 
   *  This function does the bare minimum of validation of the offsets and
   *  input types. The value_offsets are assumed to be well-formed.
   * 
   *  @param type_ids [in] An array of 8-bit signed integers, enumerated from
   *  0 corresponding to each type.
   *  @param value_offsets [in] An array of signed int32 values indicating the
   *  relative offset into the respective child array for the type in a given slot.
   *  The respective offsets for each child value array must be in order / increasing.
   *  @param children [in] Vector of children Arrays containing the data for each type.
   *  @param field_names [in] Vector of strings containing the name of each field.
   *  @param type_codes [in] Vector of type codes.
   *  @param out [out] Will have length equal to value_offsets.length() */
  
  ///
  ///
  public static native @ByVal Status MakeDense(@Const @ByRef Array type_ids, @Const @ByRef Array value_offsets,
                            @Const @ByRef ArrayVector children,
                            @Const @ByRef StringVector field_names,
                            @Cast("uint8_t*") @StdVector BytePointer type_codes,
                            @SharedPtr Array out);
  public static native @ByVal Status MakeDense(@Const @ByRef Array type_ids, @Const @ByRef Array value_offsets,
                            @Const @ByRef ArrayVector children,
                            @Const @ByRef StringVector field_names,
                            @Cast("uint8_t*") @StdVector ByteBuffer type_codes,
                            @SharedPtr Array out);
  public static native @ByVal Status MakeDense(@Const @ByRef Array type_ids, @Const @ByRef Array value_offsets,
                            @Const @ByRef ArrayVector children,
                            @Const @ByRef StringVector field_names,
                            @Cast("uint8_t*") @StdVector byte[] type_codes,
                            @SharedPtr Array out);

  /** \brief Construct Dense UnionArray from types_ids, value_offsets and children
   * 
   *  This function does the bare minimum of validation of the offsets and
   *  input types. The value_offsets are assumed to be well-formed.
   * 
   *  @param type_ids [in] An array of 8-bit signed integers, enumerated from
   *  0 corresponding to each type.
   *  @param value_offsets [in] An array of signed int32 values indicating the
   *  relative offset into the respective child array for the type in a given slot.
   *  The respective offsets for each child value array must be in order / increasing.
   *  @param children [in] Vector of children Arrays containing the data for each type.
   *  @param field_names [in] Vector of strings containing the name of each field.
   *  @param out [out] Will have length equal to value_offsets.length() */
  
  ///
  ///
  public static native @ByVal Status MakeDense(@Const @ByRef Array type_ids, @Const @ByRef Array value_offsets,
                            @Const @ByRef ArrayVector children,
                            @Const @ByRef StringVector field_names,
                            @SharedPtr Array out);

  /** \brief Construct Dense UnionArray from types_ids, value_offsets and children
   * 
   *  This function does the bare minimum of validation of the offsets and
   *  input types. The value_offsets are assumed to be well-formed.
   * 
   *  @param type_ids [in] An array of 8-bit signed integers, enumerated from
   *  0 corresponding to each type.
   *  @param value_offsets [in] An array of signed int32 values indicating the
   *  relative offset into the respective child array for the type in a given slot.
   *  The respective offsets for each child value array must be in order / increasing.
   *  @param children [in] Vector of children Arrays containing the data for each type.
   *  @param type_codes [in] Vector of type codes.
   *  @param out [out] Will have length equal to value_offsets.length() */
  
  ///
  ///
  ///
  public static native @ByVal Status MakeDense(@Const @ByRef Array type_ids, @Const @ByRef Array value_offsets,
                            @Const @ByRef ArrayVector children,
                            @Cast("uint8_t*") @StdVector BytePointer type_codes,
                            @SharedPtr Array out);
  public static native @ByVal Status MakeDense(@Const @ByRef Array type_ids, @Const @ByRef Array value_offsets,
                            @Const @ByRef ArrayVector children,
                            @Cast("uint8_t*") @StdVector ByteBuffer type_codes,
                            @SharedPtr Array out);
  public static native @ByVal Status MakeDense(@Const @ByRef Array type_ids, @Const @ByRef Array value_offsets,
                            @Const @ByRef ArrayVector children,
                            @Cast("uint8_t*") @StdVector byte[] type_codes,
                            @SharedPtr Array out);

  /** \brief Construct Dense UnionArray from types_ids, value_offsets and children
   * 
   *  This function does the bare minimum of validation of the offsets and
   *  input types. The value_offsets are assumed to be well-formed.
   * 
   *  The name of each field is filled by the index of the field.
   * 
   *  @param type_ids [in] An array of 8-bit signed integers, enumerated from
   *  0 corresponding to each type.
   *  @param value_offsets [in] An array of signed int32 values indicating the
   *  relative offset into the respective child array for the type in a given slot.
   *  The respective offsets for each child value array must be in order / increasing.
   *  @param children [in] Vector of children Arrays containing the data for each type.
   *  @param out [out] Will have length equal to value_offsets.length() */
  
  ///
  ///
  public static native @ByVal Status MakeDense(@Const @ByRef Array type_ids, @Const @ByRef Array value_offsets,
                            @Const @ByRef ArrayVector children,
                            @SharedPtr Array out);

  /** \brief Construct Sparse UnionArray from type_ids and children
   * 
   *  This function does the bare minimum of validation of the offsets and
   *  input types.
   * 
   *  @param type_ids [in] An array of 8-bit signed integers, enumerated from
   *  0 corresponding to each type.
   *  @param children [in] Vector of children Arrays containing the data for each type.
   *  @param field_names [in] Vector of strings containing the name of each field.
   *  @param type_codes [in] Vector of type codes.
   *  @param out [out] Will have length equal to type_ids.length() */
  
  ///
  ///
  public static native @ByVal Status MakeSparse(@Const @ByRef Array type_ids,
                             @Const @ByRef ArrayVector children,
                             @Const @ByRef StringVector field_names,
                             @Cast("uint8_t*") @StdVector BytePointer type_codes,
                             @SharedPtr Array out);
  public static native @ByVal Status MakeSparse(@Const @ByRef Array type_ids,
                             @Const @ByRef ArrayVector children,
                             @Const @ByRef StringVector field_names,
                             @Cast("uint8_t*") @StdVector ByteBuffer type_codes,
                             @SharedPtr Array out);
  public static native @ByVal Status MakeSparse(@Const @ByRef Array type_ids,
                             @Const @ByRef ArrayVector children,
                             @Const @ByRef StringVector field_names,
                             @Cast("uint8_t*") @StdVector byte[] type_codes,
                             @SharedPtr Array out);

  /** \brief Construct Sparse UnionArray from type_ids and children
   * 
   *  This function does the bare minimum of validation of the offsets and
   *  input types.
   * 
   *  @param type_ids [in] An array of 8-bit signed integers, enumerated from
   *  0 corresponding to each type.
   *  @param children [in] Vector of children Arrays containing the data for each type.
   *  @param field_names [in] Vector of strings containing the name of each field.
   *  @param out [out] Will have length equal to type_ids.length() */
  
  ///
  ///
  public static native @ByVal Status MakeSparse(@Const @ByRef Array type_ids,
                             @Const @ByRef ArrayVector children,
                             @Const @ByRef StringVector field_names,
                             @SharedPtr Array out);

  /** \brief Construct Sparse UnionArray from type_ids and children
   * 
   *  This function does the bare minimum of validation of the offsets and
   *  input types.
   * 
   *  @param type_ids [in] An array of 8-bit signed integers, enumerated from
   *  0 corresponding to each type.
   *  @param children [in] Vector of children Arrays containing the data for each type.
   *  @param type_codes [in] Vector of type codes.
   *  @param out [out] Will have length equal to type_ids.length() */
  
  ///
  ///
  ///
  public static native @ByVal Status MakeSparse(@Const @ByRef Array type_ids,
                             @Const @ByRef ArrayVector children,
                             @Cast("uint8_t*") @StdVector BytePointer type_codes,
                             @SharedPtr Array out);
  public static native @ByVal Status MakeSparse(@Const @ByRef Array type_ids,
                             @Const @ByRef ArrayVector children,
                             @Cast("uint8_t*") @StdVector ByteBuffer type_codes,
                             @SharedPtr Array out);
  public static native @ByVal Status MakeSparse(@Const @ByRef Array type_ids,
                             @Const @ByRef ArrayVector children,
                             @Cast("uint8_t*") @StdVector byte[] type_codes,
                             @SharedPtr Array out);

  /** \brief Construct Sparse UnionArray from type_ids and children
   * 
   *  This function does the bare minimum of validation of the offsets and
   *  input types.
   * 
   *  The name of each field is filled by the index of the field.
   * 
   *  @param type_ids [in] An array of 8-bit signed integers, enumerated from
   *  0 corresponding to each type.
   *  @param children [in] Vector of children Arrays containing the data for each type.
   *  @param out [out] Will have length equal to type_ids.length() */
  public static native @ByVal Status MakeSparse(@Const @ByRef Array type_ids,
                             @Const @ByRef ArrayVector children,
                             @SharedPtr Array out);

  /** Note that this buffer does not account for any slice offset */
  public native @SharedPtr @ByVal ArrowBuffer type_ids();

  /** Note that this buffer does not account for any slice offset */
  public native @SharedPtr @ByVal ArrowBuffer value_offsets();

  public native int value_offset(@Cast("int64_t") long i);

  public native @Cast("const arrow::UnionArray::type_id_t*") BytePointer raw_type_ids();
  public native @Const IntPointer raw_value_offsets();

  public native @Const UnionType union_type();

  public native UnionMode.type mode();

  // Return the given field as an individual array.
  // For sparse unions, the returned array has its offset, length and null
  // count adjusted.
  // For dense unions, the returned array is unchanged.
  public native @SharedPtr @ByVal Array child(int pos);
}
