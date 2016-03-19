// Targeted by JavaCPP version 1.2-SNAPSHOT

package org.bytedeco.javacpp;

import org.bytedeco.javacpp.annotation.Index;
import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_videoio.*;
import static org.bytedeco.javacpp.opencv_highgui.*;
import static org.bytedeco.javacpp.opencv_flann.*;
import static org.bytedeco.javacpp.opencv_ml.*;
import static org.bytedeco.javacpp.opencv_features2d.*;
import static org.bytedeco.javacpp.opencv_calib3d.*;
import static org.bytedeco.javacpp.opencv_video.*;

public class chilitags extends org.bytedeco.javacpp.presets.chilitags {
    static { Loader.load(); }

@Name("std::map<int,chilitags::Quad>") public static class TagCornerMap extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public TagCornerMap(Pointer p) { super(p); }
    public TagCornerMap()       { allocate();  }
    private native void allocate();
    public native @Name("operator=") @ByRef TagCornerMap put(@ByRef TagCornerMap x);

    public native long size();

    @Index public native @ByRef @Cast("chilitags::Quad*") FloatPointer get(int i);
    public native TagCornerMap put(int i, FloatPointer value);

    public native @ByVal Iterator begin();
    public native @ByVal Iterator end();
    @NoOffset @Name("iterator") public static class Iterator extends Pointer {
        public Iterator(Pointer p) { super(p); }
        public Iterator() { }

        public native @Name("operator++") @ByRef Iterator increment();
        public native @Name("operator==") boolean equals(@ByRef Iterator it);
        public native @Name("operator*().first") @MemberGetter int first();
        public native @Name("operator*().second") @MemberGetter @ByRef @Cast("chilitags::Quad*") FloatPointer second();
    }
}

@Name("std::map<std::string,chilitags::Chilitags3D_<float>::TransformMatrix>") public static class TagPoseMap extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public TagPoseMap(Pointer p) { super(p); }
    public TagPoseMap()       { allocate();  }
    private native void allocate();
    public native @Name("operator=") @ByRef TagPoseMap put(@ByRef TagPoseMap x);

    public native long size();

    @Index public native @ByRef @Cast("chilitags::Chilitags3D_<float>::TransformMatrix*") FloatPointer get(@StdString BytePointer i);
    public native TagPoseMap put(@StdString BytePointer i, FloatPointer value);

    public native @ByVal Iterator begin();
    public native @ByVal Iterator end();
    @NoOffset @Name("iterator") public static class Iterator extends Pointer {
        public Iterator(Pointer p) { super(p); }
        public Iterator() { }

        public native @Name("operator++") @ByRef Iterator increment();
        public native @Name("operator==") boolean equals(@ByRef Iterator it);
        public native @Name("operator*().first") @MemberGetter @StdString BytePointer first();
        public native @Name("operator*().second") @MemberGetter @ByRef @Cast("chilitags::Chilitags3D_<float>::TransformMatrix*") FloatPointer second();
    }
}

@Name("std::map<std::string,chilitags::Chilitags3D_<double>::TransformMatrix>") public static class TagPoseMapd extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public TagPoseMapd(Pointer p) { super(p); }
    public TagPoseMapd()       { allocate();  }
    private native void allocate();
    public native @Name("operator=") @ByRef TagPoseMapd put(@ByRef TagPoseMapd x);

    public native long size();

    @Index public native @ByRef @Cast("chilitags::Chilitags3D_<double>::TransformMatrix*") DoublePointer get(@StdString BytePointer i);
    public native TagPoseMapd put(@StdString BytePointer i, DoublePointer value);

    public native @ByVal Iterator begin();
    public native @ByVal Iterator end();
    @NoOffset @Name("iterator") public static class Iterator extends Pointer {
        public Iterator(Pointer p) { super(p); }
        public Iterator() { }

        public native @Name("operator++") @ByRef Iterator increment();
        public native @Name("operator==") boolean equals(@ByRef Iterator it);
        public native @Name("operator*().first") @MemberGetter @StdString BytePointer first();
        public native @Name("operator*().second") @MemberGetter @ByRef @Cast("chilitags::Chilitags3D_<double>::TransformMatrix*") DoublePointer second();
    }
}

// Parsed from chilitags/chilitags.hpp

/*******************************************************************************
*   Copyright 2013-2014 EPFL                                                   *
*   Copyright 2013-2014 Quentin Bonnard                                        *
*                                                                              *
*   This file is part of chilitags.                                            *
*                                                                              *
*   Chilitags is free software: you can redistribute it and/or modify          *
*   it under the terms of the Lesser GNU General Public License as             *
*   published by the Free Software Foundation, either version 3 of the         *
*   License, or (at your option) any later version.                            *
*                                                                              *
*   Chilitags is distributed in the hope that it will be useful,               *
*   but WITHOUT ANY WARRANTY; without even the implied warranty of             *
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              *
*   GNU Lesser General Public License for more details.                        *
*                                                                              *
*   You should have received a copy of the GNU Lesser General Public License   *
*   along with Chilitags.  If not, see <http://www.gnu.org/licenses/>.         *
*******************************************************************************/

// #ifndef Chilitags_HPP
// #define Chilitags_HPP

// #include <map>
// #include <vector>
// #include <opencv2/core/core.hpp>
// #include <string>
// #include <memory>

/**
    The location of the detected chilitags are stored in a 4x2 matrix
    corresponding to the outside corners of its black border. The rows
    correspond to the 2D coordinates of the corners. The corners are
    consistenly stored clockwise, starting from top-left, i.e.
    <pre>{@literal
    {    top-left.x  ,    top-left.y  ,
         top-right.x ,    top-right.y ,
      bottom-right.x , bottom-left.y  ,
      bottom-left.x  , bottom-left.y  }
    }</pre>
 */

/**
    This class is the core of detection of chilitags.
    <p>
    Its main function is to find tags in an image, i.e. return the id and the
    position in the image of the corners of each detected chilitag.
    <p>
    It also provides some utilities, like encoding and decoding id's to/from
    bit matrices, or drawing a given tag.
 */
@Namespace("chilitags") @NoOffset public static class Chilitags extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public Chilitags(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public Chilitags(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public Chilitags position(long position) {
        return (Chilitags)super.position(position);
    }


/**
    Constructs a object ready to detect tags.
    It sets a default persistence of 5 frames for the tags, and a gain of 0.
    Please refer to the documentation of setFilter() for more detail.
    The default detection performance is balanced between accuracy and
    processing time (see Chilitags::FAST); it can be changed with
    setPerformance().
 */
public Chilitags() { super((Pointer)null); allocate(); }
private native void allocate();

/**
    Parameters to paliate with the imperfections of the detection.
    <p>
    @param persistence the number of frames in which a tag should be absent
    before being removed from the output of find(). 0 means that tags disappear
    directly if they are not detected.
    <p>
    @param gain a value between 0 and 1 corresponding to the weight of the
    previous (filtered) position in the new filtered position. 0 means that the
    latest position of the tag is returned.
 */
public native void setFilter(int persistence, float gain);

/**
    Values of the parameter to tell find() how to combine tracking and full
    detection.
    <p>
    find() relies on two different techniques to localize 2D tags in an image:
    \li The *detection* searches for edges in the full input image, keeps those
    wich looke like a quadrilateral, and check whether there is a valid
    bitmatrix inside it.
    \li The *tracking* compares two succesive input images and tries to update
    the position of tags that were previously detected. This process is
    significantly faster than the full detection, but can not detect new tags.
*/
/** enum chilitags::Chilitags::DetectionTrigger */
public static final int
/**
    First track results of the previous call to find(), then run a full
    detection on the same input image. The detected position overrides the
    position resulting from tracking if the same tag is both tracked and
    detected. 
    <p>
    This improves the robustness of the detection, e.g. in the case where the
    tag has already been detected previously, but moves too fast to be detected
    again.
*/
    TRACK_AND_DETECT = 0,

/**
    Disable tracking: only full detections are performed. Compared to
    {@code TRACK_AND_DETECT}, {@code DETECT_ONLY} leads to a marginally faster processing,
    but may result in decreased detection performances when the tags move (due
    to motion blur).
     <p>
     {@code DETECT_ONLY} is however useful when Chilitags processes sequence of
     unrelated images, e.g.  in the batch processing of still images. In this
     case, tracking is useless and most likely generates false positives.
*/
    DETECT_ONLY = 1,

/**
    Perform tracking only. Tracking is drastically faster than full detection,
    but it can only report tags that have been already detected once: full
    detection must be run at least once to have some tags to track
    <p>
    Likewise, tracking can not detect new tags. A full detection needs to be
    run explicitely to detect (and then track) those. {@code TRACK_ONLY} is hence
    most useful when full control of when full detections occur is required
    (typically when precise control the time spend on processing one frame is
    needed). Use {@code DETECT_PERIODICALLY} to have automatic re-detection of tags
    every few frames.
    <p>
    Another interesting use case is to call {@code find()} with {@code TRACK_ONLY} as long
    as an expected (set of) tag(s) is found, and with {@code DETECT_ONLY} otherwise.
*/
    TRACK_ONLY = 2,

/**
    Periodically run a full detection.
    <p>
    {@code DETECT_PERIODICALLY} lets Chilitags use tracking most of the time, and
    eventually run a full detection.
    <p>
    {@code setDetectionPeriod()} allows to specify the number of frames between two
    full detection. It defaults to 15, i.e. out of 15 consecutive calls to
    {@code find()}, 1 will use a full detection, and the 14 others will only track
    previous results.
*/
    DETECT_PERIODICALLY = 3,

    /**
     * \brief Runs the detection in the background, with a period
     *
     * Runs the detection in a background thread, only tracking in the call to
     * {@code find()}.
     *
     * {@code setDetectionPeriod()} allows to specify the number of calls between two
     * detections. It defaults to 15, i.e. out of 15 consecutive calls to
     * {@code find()}, the background thread will be informed to run detection. After
     * this, a new detection will be done as soon as a new image frame is
     * presented in the call to {@code find()}. If the background thread takes more
     * time than 15 calls to {@code find()}, it will be running as frequently as
     * possible, i.e the same as {@code BACKGROUND_DETECT_ALWAYS}.
     *
     * This cannot be used without enabling multithreading support during
     * build.
     */
    ASYNC_DETECT_PERIODICALLY = 4,

    /**
     * \brief Runs the detection in the background, as frequently as possible
     *
     * Runs the detection in a background thread, only tracking in the call to
     * {@code find()}. The detection is run as frequently as possible, i.e a new
     * detection is started as soon as the new image frame is presented in the
     * call to {@code find()} after the previous detection is finished.
     *
     * This cannot be used without enabling multithreading support during
     * build.
     */
    ASYNC_DETECT_ALWAYS = 5;

/**
    This is the main method of Chilitags.
    <p>
    @return the detected tags, in the form of a mapping between their id's and
    the position of their four corners.
    <p>
    @param inputImage an OpenCV image (gray or BGR)
    <p>
    @param detectionTrigger specifies how to combine tracking and full
    detection. Tracking is drastically faster, but it can at best return tags
    previously found; it won't find new ones, but can lose some. See
    Chilitags::DetectionTrigger for a description of the possible values.
 */
public native @ByVal TagCornerMap find(
    @Const @ByRef Mat inputImage,
    @Cast("chilitags::Chilitags::DetectionTrigger") int detectionTrigger/*=chilitags::Chilitags::DETECT_ONLY*/);
public native @ByVal TagCornerMap find(
    @Const @ByRef Mat inputImage);

/**
    When the detection trigger is Chilitags::DETECT_PERIODICALLY, {@code period}
    specifies the number of frames between each full detection. The
    default is 15, which means that out of 15 consecutive calls to find(),
    one will use a full detection, and the 14 others will only track
    previous results.
*/
public native void setDetectionPeriod(int period);

/**
    Preset groups of parameters (for setPerformance()) to adjust  the
    compromise between processing time and accuracy of detection.
 */
/** enum chilitags::Chilitags::PerformancePreset */
public static final int
/**
    Favor speed over accuracy: no corner refinment, no subsampling.
*/
    FASTER = 0,
/**
    Balance speed and accuracy (default): corners are refined, no subsampling.
*/
    FAST = 1,
/**
    Favor robustness over accuracy: corner are refined, input is
    subsampled down to 160 pixels wide.
*/
    ROBUST = 2;

/**
    Applies one of the performance tuning preset (See PerformancePreset). To
    tune more finely the performance trade-offs, see setCornerRefinment(),
    setMaxInputWidth(), and setMinInputWidth().
*/
public native void setPerformance(@Cast("chilitags::Chilitags::PerformancePreset") int preset);

//@{

/**
    Enable or disable the corner refinement. It is enabled (true) by default.
    When disabled, the processing time is reduced by ~33%, but the coordinates
    of the tags lose their sub-pixel precision, and there is a marginally
    higher level of false negatives.
 */
public native void setCornerRefinement(@Cast("bool") boolean refineCorners);

/**
    Ensures that the image used as input for the detection is at most
    {@code maxWidth} wide. The smaller, the faster, but tags smaller than 20 pixels
    won't be detected.
    <p>
    @param maxWidth the width to which input images should be reduced to, or 0
    if no resizing should occur (default).
 */
public native void setMaxInputWidth(int maxWidth);

/**
    Chilitags searches for tags on the input image and on subsamples reduced to
    50%, 25%, 12.5%, etc. of the original size. The subsamples are reduced as
    long as they are at least {@code minWidth} wide. This value can be changed to
    adjust the lower limit of subsampling. For example, the Chilitags::ROBUST
    performance preset calls {@code setMinInputWidth(160)}.
    <p>
    If {@code minWidth} is set to 0, subsampling is completely disabled, i.e. tags
    are searched only on the original input image. This is the behaviour set by
    Chilitags::FAST, i.e. the default behaviour.
    <p>
    Disabling the subsampling reduces the processing time by ~40%, but large
    tags (having sides larger than hundreds of pixels) are likely to be missed.
 */
public native void setMinInputWidth(int minWidth);

//@}

//@{
/**
    Finds the black and white, 6x6 matrix corresponding to the given id.
    <p>
    @param id the id of the tag to encode, between  0 (included) and 1024
    (excluded).
    <p>
    @return the 36-element bit matrix coding the given id (black is 0,
    white is 1)
 */

/**
    Finds the tag id corresponding given the black and white, 6x6 matrix.
    <p>
    @return the id decoded from the bit matrix, between  0 (included) and 1024
    (excluded). If the bit matrix did not code a valid id, -1 is returned.
    <p>
    @param bits the 36-element bit matrix coding the given id (black is 0,
    white is 1)
 */

/**
    @return an OpenCV image of a given tag.
    <p>
    @param id the id of the tag to draw, between [0,1024)
    <p>
    @param cellSize the (integer) scale factor with which to draw the tag. In
    other words, every bit of the data matrix of the tag will be {@code cellSize}
    large.
    <p>
    @param withMargin a boolean coding whether the returned image of the tag
    should be surrounded by a white frame, ensuring that the edges of the tag
    will contrast with the background.
    <p>
    @param color the RGB color with which to draw the tag. Values are integers
    within [0,255]. The darker, the better. Black is default and optimal.
 */
public native @ByVal Mat draw(
    int id,
    int cellSize/*=1*/,
    @Cast("bool") boolean withMargin/*=false*/,
    @ByVal(nullValue = "cv::Scalar(0,0,0)") Scalar color/*=cv::Scalar(0,0,0)*/);
public native @ByVal Mat draw(
    int id);

//@}

}

/**
    Chilitags3D aims at recovering the 3D pose (i.e. the 3D position and the 3D
    rotation) of chilitags. It embeds a Chilitags instance to take care of the
    (2D) detection.
 */
@Name("chilitags::Chilitags3D_<float>") @NoOffset public static class Chilitags3D extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public Chilitags3D(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public Chilitags3D(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public Chilitags3D position(long position) {
        return (Chilitags3D)super.position(position);
    }


/**
    Creates an object ready to find the 3D pose of chilitags.
    <p>
    By default, Chilitags3D assumes an arbitrary, but reasonnable focal length
    (700), and expects the dimensions of the captured images. In this
    configuration, the depth estimation makes sense, but it is not accurate. In
    order to correctly estimate the 3D pose, the intrinsic calibration
    parameters of your camera needs to be provided. To do so, use the
    readCalibration() or setCalibration() methods.
    <p>
    Chilitags3D also assumes by default that the 3D pose of every detected tag
    is expected, and that every tag is independent from the others, and that
    they are 20 millimetres wide. The method read3DConfiguration() can be used
    to specify which tags are of interest, and how they are arranged on a rigid
    object, and how big they are.
    <p>
    To first detect th tags in the image, Chilitags3D creates a Chilitags
    instance, which can be accessed through the getChilitags() accessors. This
    Chilitags instance is set to have a persistence of 0, because Chilitags3D
    uses a more advanced Kalman filter. See enableFilter() and setPersistence()
    for more details.
    <p>
    You can also create yourself a separate instance of Chilitagsfor the 2D
    detection of tags and use it by calling
    Chilitags3D::estimate(const TagCornerMap &tags)
    with the output of
    Chilitags::find(const cv::Mat &inputImage)
    instead of calling directly
    Chilitags3D::estimate(const cv::Mat &inputImage).
    <p>
    @param cameraResolution Resolution of the camera used as input (640x480 by
    default). This parameter is only used to provide meaningful pose
    estimation. Input images of different resolution can be provided to the
    detection anyway. This parameter is overridden by readCalibration() or
    setCalibration().
 */
public Chilitags3D(@ByVal(nullValue = "cv::Size(640, 480)") Size cameraResolution/*=cv::Size(640, 480)*/) { super((Pointer)null); allocate(cameraResolution); }
private native void allocate(@ByVal(nullValue = "cv::Size(640, 480)") Size cameraResolution/*=cv::Size(640, 480)*/);
public Chilitags3D() { super((Pointer)null); allocate(); }
private native void allocate();

/** Accessor to the underlying (2D) Chilitags detection. */

/** Accessor to the underlying (2D) Chilitags detection. */
public native @ByRef Chilitags getChilitags();

/**
    @return a mapping of the detected objects to their transformation
    matrices. Transformation matrices are row-major and follow the standard
    convention to code the rotation and translation parameters in homogeneous
    coordinates:
    <pre>{@literal
    { r11 , r12 , r13 , tx 
      r21 , r22 , r23 , ty 
      r31 , r32 , r33 , tz 
        0 ,   0 ,   0 ,  1 }
    }</pre>
    @param tags a list of tags, as returned by Chilitags::find().
    <p>
    @param camDeltaR Rotation from the previous camera frame to
    the current camera frame, i.e rotation of the current camera frame in the
    last camera frame. Quaternion format (scalar, vx, vy, vz).
    <p>
    @param camDeltaX Translation from the previous camera frame
    to the current camera frame, i.e position of the current camera frame in
    the last camera frame.
 */
public native @ByVal TagPoseMap estimate(@Const @ByRef TagCornerMap tags);

/**
    This is a convenience variant of estimate() which also takes care of the
    detection.
    <p>
    @return a mapping of the detected objects to their transformation
    matrices. Transformation matrices are row-major and follow the standard
    convention to code the rotation and translation parameters in homogeneous
    coordinates:
    <pre>{@literal
    { r11 , r12 , r13 , tx 
      r21 , r22 , r23 , ty 
      r31 , r32 , r33 , tz 
        0 ,   0 ,   0 ,  1 }
    }</pre>
    <p>
    @param inputImage the image to feed to Chilitags::find().
    <p>
    @param detectionTrigger specifies how to combine tracking and
    full detection. Tracking is drastically faster, but it can at
    best return tags previously found; it won't find new ones, but
    can lose some. See Chilitags::DetectionTrigger for a description of the
    possible values.
    <p>
    @param camDeltaR Rotation from the previous camera frame to
    the current camera frame, i.e rotation of the current camera frame in the
    last camera frame. Quaternion format (scalar, vx, vy, vz).
    <p>
    @param camDeltaX Translation from the previous camera frame
    to the current camera frame, i.e position of the current camera frame in
    the last camera frame.
 */
public native @ByVal TagPoseMap estimate(
    @Const @ByRef Mat inputImage);

/**
    Chilitags3D can also detect rigid assemblies of tags. This allows for a
    more precise estimation of the object holding the tag, and for a graceful
    degradation of the estimation, should some of the tag be misdetected or
    occluded.
    <p>
    @param filenameOrString The name of the YAML configuration file (or the whole
    file itself as a string) describing rigid clusters of tags. The library is
    distributed with a sample configuration file documenting the expected format.
    <p>
    @param omitOtherTags If true, ignore the tags that are not explicitly
    listed in the configuration file. If false (default),
    Chilitags3D::estimate() estimates the 3D pose of all the detected tags. You
    can set the size of tags not described in the configuration file with
    setDefaultTagSize().
    <p>
    @param readFromString If true, will read tag configuration directly from the
    given string. If false (default) will open the file with the given name and
    try to read the configuration from there.
    <p>
    @return Whether reading the configuration was successful
 */
public native @Cast("bool") boolean readTagConfiguration(
    @StdString BytePointer filenameOrString,
    @Cast("bool") boolean omitOtherTags/*=false*/,
    @Cast("bool") boolean readFromString/*=false*/);
public native @Cast("bool") boolean readTagConfiguration(
    @StdString BytePointer filenameOrString);
public native @Cast("bool") boolean readTagConfiguration(
    @StdString String filenameOrString,
    @Cast("bool") boolean omitOtherTags/*=false*/,
    @Cast("bool") boolean readFromString/*=false*/);
public native @Cast("bool") boolean readTagConfiguration(
    @StdString String filenameOrString);

/**
    Sets the default size of tags (used to compute their 3D pose) when not
    explicitly specified with read3DConfiguration(). To be accurate, the unit
    must match the unit used for the camera calibration (usually, millimetres).
    <p>
    Note that is assumes all the tags have the same size. If tags have
    different size, you may want to list them in the configuration file (see
    read3DConfiguration()).
    <p>
    The default value of the default tag size is 20 millimetres.
 */
public native void setDefaultTagSize(float defaultSize);

/**
 * \brief Enables/disables Kalman filtering on tag pose (enabled by default)
 *
 * @param enabled Whether to enable Kalman filtering
 */
public native void enableFilter(@Cast("bool") boolean enabled);

/**
 * \brief Sets the persistence of tags against being discarded when not
 * observed (10 by default)
 *
 * @param persistence Persistence value, roughly correponds to number of frames
 */
public native void setPersistence(float persistence);

/**
 * \brief Sets the process noise covariance matrix a.k.a Q for the Kalman filter
 *
 * The state is described by (x,y,z,qw,qx,qy,qz) where x,y,z is the tag
 * position and qw,qx,qy,qz is the tag orientation in quaternion
 * representation. Therefore, the input matrix should correspond to
 * cov((x,y,z,qw,qx,qy,qz)).
 *
 * When IMU data is present, the process moves the tag according to camera
 * movement. When IMU data is not present, the process tries to keep the tag
 * still. Smaller values in the process covariance matrix causes the tags to
 * resist motion more. By default, this matrix has
 * 1e-3, 1e-3, 1e-3, 1e-4, 1e-4, 1e-4, 1e-4
 * on its diagonal.
 *
 * @param covariance 7x7 covariance matrix
 */
public native void setFilterProcessNoiseCovariance(@Const({false, true}) @ByRef Mat covariance);

/**
 * \brief Sets the observation noise covariance matrix a.k.a R for the Kalman filter
 *
 * The observation (done by image processing on the camera image) is described
 * by (x,y,z,qw,qx,qy,qz) where x,y,z is the tag position and qw,qx,qy,qz is
 * the tag orientation in quaternion representation. Therefore, the input
 * matrix should correspond to cov((x,y,z,qw,qx,qy,qz)).
 *
 * Larger values in the observation noise covariance matrix indicates noisier
 * measurements and causes observations to affect the state less, making tags
 * more resistant to motion. By default, this matrix has
 * 1e-3, 1e-3, 1e-1, 1e-3, 1e-2, 1e-2, 1e-5
 * on its diagonal.
 *
 * @param covariance 7x7 covariance matrix
 */
public native void setFilterObservationNoiseCovariance(@Const({false, true}) @ByRef Mat covariance);

/**
    For accurate results, Chilitags3D can be provided the calibration data of
    the camera detecting the chilitags.  See
    https://docs.opencv.org/modules/calib3d/doc/camera_calibration_and_3d_reconstruction.html
    for background on this topic.
    <p>
    Note that this method can be called as often as needed with a new calibration
    configuration (for instance if the user switched to another camera).
    <p>
    @param newCameraMatrix the 3x3 matrix of the camera intrinsics (see
    https://en.wikipedia.org/wiki/Camera_resectioning#Intrinsic_parameters).
    @param newDistCoeffs a vector containing the distortion coefficients.
 */
public native void setCalibration(@ByVal Mat newCameraMatrix,
                    @ByVal Mat newDistCoeffs);

/**
    For accurate results, Chilitags3D can be provided the calibration data of
    the camera detecting the chilitags.  See
    http://docs.opencv.org/modules/calib3d/doc/camera_calibration_and_3d_reconstruction.html
    for background on this topic.
    <p>
    Note that this method can be called as often as needed with a new calibration
    configuration (for instance if the user switched to another camera).
    <p>
    This method is similar to setCalibration, but reads the camera calibration
    information directly from a file, as generated by OpenCV's 'calibration'
    sample.
    <p>
    @param filename the path to a file containing the calibration data
    @return the size of the images used to generate the calibration data.
 */
public native @ByVal Size readCalibration(@StdString BytePointer filename);
public native @ByVal Size readCalibration(@StdString String filename);

/**
    Returns the camera matrix used for the pose estimation.
*/
public native @Const @ByRef Mat getCameraMatrix();

/**
    Returns the distortion coefficients used for the pose estimation.
*/
public native @Const @ByRef Mat getDistortionCoeffs();

}
@Name("chilitags::Chilitags3D_<double>") @NoOffset public static class Chilitags3Dd extends Pointer {
    static { Loader.load(); }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public Chilitags3Dd(Pointer p) { super(p); }
    /** Native array allocator. Access with {@link Pointer#position(long)}. */
    public Chilitags3Dd(long size) { super((Pointer)null); allocateArray(size); }
    private native void allocateArray(long size);
    @Override public Chilitags3Dd position(long position) {
        return (Chilitags3Dd)super.position(position);
    }


/**
    Creates an object ready to find the 3D pose of chilitags.
    <p>
    By default, Chilitags3D assumes an arbitrary, but reasonnable focal length
    (700), and expects the dimensions of the captured images. In this
    configuration, the depth estimation makes sense, but it is not accurate. In
    order to correctly estimate the 3D pose, the intrinsic calibration
    parameters of your camera needs to be provided. To do so, use the
    readCalibration() or setCalibration() methods.
    <p>
    Chilitags3D also assumes by default that the 3D pose of every detected tag
    is expected, and that every tag is independent from the others, and that
    they are 20 millimetres wide. The method read3DConfiguration() can be used
    to specify which tags are of interest, and how they are arranged on a rigid
    object, and how big they are.
    <p>
    To first detect th tags in the image, Chilitags3D creates a Chilitags
    instance, which can be accessed through the getChilitags() accessors. This
    Chilitags instance is set to have a persistence of 0, because Chilitags3D
    uses a more advanced Kalman filter. See enableFilter() and setPersistence()
    for more details.
    <p>
    You can also create yourself a separate instance of Chilitagsfor the 2D
    detection of tags and use it by calling
    Chilitags3D::estimate(const TagCornerMap &tags)
    with the output of
    Chilitags::find(const cv::Mat &inputImage)
    instead of calling directly
    Chilitags3D::estimate(const cv::Mat &inputImage).
    <p>
    @param cameraResolution Resolution of the camera used as input (640x480 by
    default). This parameter is only used to provide meaningful pose
    estimation. Input images of different resolution can be provided to the
    detection anyway. This parameter is overridden by readCalibration() or
    setCalibration().
 */
public Chilitags3Dd(@ByVal(nullValue = "cv::Size(640, 480)") Size cameraResolution/*=cv::Size(640, 480)*/) { super((Pointer)null); allocate(cameraResolution); }
private native void allocate(@ByVal(nullValue = "cv::Size(640, 480)") Size cameraResolution/*=cv::Size(640, 480)*/);
public Chilitags3Dd() { super((Pointer)null); allocate(); }
private native void allocate();

/** Accessor to the underlying (2D) Chilitags detection. */

/** Accessor to the underlying (2D) Chilitags detection. */
public native @ByRef Chilitags getChilitags();

/**
    @return a mapping of the detected objects to their transformation
    matrices. Transformation matrices are row-major and follow the standard
    convention to code the rotation and translation parameters in homogeneous
    coordinates:
    <pre>{@literal
    { r11 , r12 , r13 , tx 
      r21 , r22 , r23 , ty 
      r31 , r32 , r33 , tz 
        0 ,   0 ,   0 ,  1 }
    }</pre>
    @param tags a list of tags, as returned by Chilitags::find().
    <p>
    @param camDeltaR Rotation from the previous camera frame to
    the current camera frame, i.e rotation of the current camera frame in the
    last camera frame. Quaternion format (scalar, vx, vy, vz).
    <p>
    @param camDeltaX Translation from the previous camera frame
    to the current camera frame, i.e position of the current camera frame in
    the last camera frame.
 */
public native @ByVal @Cast("chilitags::Chilitags3D_<double>::TagPoseMap*") TagPoseMapd estimate(@Const @ByRef TagCornerMap tags);

/**
    This is a convenience variant of estimate() which also takes care of the
    detection.
    <p>
    @return a mapping of the detected objects to their transformation
    matrices. Transformation matrices are row-major and follow the standard
    convention to code the rotation and translation parameters in homogeneous
    coordinates:
    <pre>{@literal
    { r11 , r12 , r13 , tx 
      r21 , r22 , r23 , ty 
      r31 , r32 , r33 , tz 
        0 ,   0 ,   0 ,  1 }
    }</pre>
    <p>
    @param inputImage the image to feed to Chilitags::find().
    <p>
    @param detectionTrigger specifies how to combine tracking and
    full detection. Tracking is drastically faster, but it can at
    best return tags previously found; it won't find new ones, but
    can lose some. See Chilitags::DetectionTrigger for a description of the
    possible values.
    <p>
    @param camDeltaR Rotation from the previous camera frame to
    the current camera frame, i.e rotation of the current camera frame in the
    last camera frame. Quaternion format (scalar, vx, vy, vz).
    <p>
    @param camDeltaX Translation from the previous camera frame
    to the current camera frame, i.e position of the current camera frame in
    the last camera frame.
 */
public native @ByVal @Cast("chilitags::Chilitags3D_<double>::TagPoseMap*") TagPoseMapd estimate(
    @Const @ByRef Mat inputImage);

/**
    Chilitags3D can also detect rigid assemblies of tags. This allows for a
    more precise estimation of the object holding the tag, and for a graceful
    degradation of the estimation, should some of the tag be misdetected or
    occluded.
    <p>
    @param filenameOrString The name of the YAML configuration file (or the whole
    file itself as a string) describing rigid clusters of tags. The library is
    distributed with a sample configuration file documenting the expected format.
    <p>
    @param omitOtherTags If true, ignore the tags that are not explicitly
    listed in the configuration file. If false (default),
    Chilitags3D::estimate() estimates the 3D pose of all the detected tags. You
    can set the size of tags not described in the configuration file with
    setDefaultTagSize().
    <p>
    @param readFromString If true, will read tag configuration directly from the
    given string. If false (default) will open the file with the given name and
    try to read the configuration from there.
    <p>
    @return Whether reading the configuration was successful
 */
public native @Cast("bool") boolean readTagConfiguration(
    @StdString BytePointer filenameOrString,
    @Cast("bool") boolean omitOtherTags/*=false*/,
    @Cast("bool") boolean readFromString/*=false*/);
public native @Cast("bool") boolean readTagConfiguration(
    @StdString BytePointer filenameOrString);
public native @Cast("bool") boolean readTagConfiguration(
    @StdString String filenameOrString,
    @Cast("bool") boolean omitOtherTags/*=false*/,
    @Cast("bool") boolean readFromString/*=false*/);
public native @Cast("bool") boolean readTagConfiguration(
    @StdString String filenameOrString);

/**
    Sets the default size of tags (used to compute their 3D pose) when not
    explicitly specified with read3DConfiguration(). To be accurate, the unit
    must match the unit used for the camera calibration (usually, millimetres).
    <p>
    Note that is assumes all the tags have the same size. If tags have
    different size, you may want to list them in the configuration file (see
    read3DConfiguration()).
    <p>
    The default value of the default tag size is 20 millimetres.
 */
public native void setDefaultTagSize(double defaultSize);

/**
 * \brief Enables/disables Kalman filtering on tag pose (enabled by default)
 *
 * @param enabled Whether to enable Kalman filtering
 */
public native void enableFilter(@Cast("bool") boolean enabled);

/**
 * \brief Sets the persistence of tags against being discarded when not
 * observed (10 by default)
 *
 * @param persistence Persistence value, roughly correponds to number of frames
 */
public native void setPersistence(double persistence);

/**
 * \brief Sets the process noise covariance matrix a.k.a Q for the Kalman filter
 *
 * The state is described by (x,y,z,qw,qx,qy,qz) where x,y,z is the tag
 * position and qw,qx,qy,qz is the tag orientation in quaternion
 * representation. Therefore, the input matrix should correspond to
 * cov((x,y,z,qw,qx,qy,qz)).
 *
 * When IMU data is present, the process moves the tag according to camera
 * movement. When IMU data is not present, the process tries to keep the tag
 * still. Smaller values in the process covariance matrix causes the tags to
 * resist motion more. By default, this matrix has
 * 1e-3, 1e-3, 1e-3, 1e-4, 1e-4, 1e-4, 1e-4
 * on its diagonal.
 *
 * @param covariance 7x7 covariance matrix
 */
public native void setFilterProcessNoiseCovariance(@Const({false, true}) @ByRef Mat covariance);

/**
 * \brief Sets the observation noise covariance matrix a.k.a R for the Kalman filter
 *
 * The observation (done by image processing on the camera image) is described
 * by (x,y,z,qw,qx,qy,qz) where x,y,z is the tag position and qw,qx,qy,qz is
 * the tag orientation in quaternion representation. Therefore, the input
 * matrix should correspond to cov((x,y,z,qw,qx,qy,qz)).
 *
 * Larger values in the observation noise covariance matrix indicates noisier
 * measurements and causes observations to affect the state less, making tags
 * more resistant to motion. By default, this matrix has
 * 1e-3, 1e-3, 1e-1, 1e-3, 1e-2, 1e-2, 1e-5
 * on its diagonal.
 *
 * @param covariance 7x7 covariance matrix
 */
public native void setFilterObservationNoiseCovariance(@Const({false, true}) @ByRef Mat covariance);

/**
    For accurate results, Chilitags3D can be provided the calibration data of
    the camera detecting the chilitags.  See
    https://docs.opencv.org/modules/calib3d/doc/camera_calibration_and_3d_reconstruction.html
    for background on this topic.
    <p>
    Note that this method can be called as often as needed with a new calibration
    configuration (for instance if the user switched to another camera).
    <p>
    @param newCameraMatrix the 3x3 matrix of the camera intrinsics (see
    https://en.wikipedia.org/wiki/Camera_resectioning#Intrinsic_parameters).
    @param newDistCoeffs a vector containing the distortion coefficients.
 */
public native void setCalibration(@ByVal Mat newCameraMatrix,
                    @ByVal Mat newDistCoeffs);

/**
    For accurate results, Chilitags3D can be provided the calibration data of
    the camera detecting the chilitags.  See
    http://docs.opencv.org/modules/calib3d/doc/camera_calibration_and_3d_reconstruction.html
    for background on this topic.
    <p>
    Note that this method can be called as often as needed with a new calibration
    configuration (for instance if the user switched to another camera).
    <p>
    This method is similar to setCalibration, but reads the camera calibration
    information directly from a file, as generated by OpenCV's 'calibration'
    sample.
    <p>
    @param filename the path to a file containing the calibration data
    @return the size of the images used to generate the calibration data.
 */
public native @ByVal Size readCalibration(@StdString BytePointer filename);
public native @ByVal Size readCalibration(@StdString String filename);

/**
    Returns the camera matrix used for the pose estimation.
*/
public native @Const @ByRef Mat getCameraMatrix();

/**
    Returns the distortion coefficients used for the pose estimation.
*/
public native @Const @ByRef Mat getDistortionCoeffs();

}



// #endif


}
