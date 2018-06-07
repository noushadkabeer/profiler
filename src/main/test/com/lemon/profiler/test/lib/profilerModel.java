package com.lemon.profiler.test.lib;
<?xml version="1.0" encoding="UTF-8"?>
<model xmlns="http://www.alfresco.org/model/dictionary/1.0" name="pf:profilermodel">
    <description>Profiler Model</description>
    <author>Noushad</author>
    <version>1.0</version>
    <imports>
        <import uri="http://www.alfresco.org/model/dictionary/1.0" prefix="d"/>
        <import uri="http://www.alfresco.org/model/content/1.0" prefix="cm"/>
    </imports>
    <namespaces>
        <namespace uri="pf.lemon.model" prefix="pf"/>
    </namespaces>
    <data-types/>
    <constraints/>
    <types>
        <type name="pf:profile">
            <title>Candidate Profile</title>
            <parent>cm:content</parent>
            <properties>
                <property name="pf:profileId">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:profileName">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
				<property name="pf:firstName">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
				<property name="pf:lastName">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
				<property name="pf:profileDOB">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:profileTitle">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:profileExperience">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
				<property name="pf:profileTotalExperience">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
				<property name="pf:profileIndustry">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:profileEducation">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:profileQualification">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
				<property name="pf:profileSkills">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
				<property name="pf:profileSpecialization">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:profileInterests">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:profileLocation">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:profileAddress">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:profileSummary">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
            </properties>
            <associations>
                <association name="pf:profileUploaded">
                    <source>
                        <mandatory>false</mandatory>
                        <many>true</many>
                    </source>
                    <target>
                        <class>cm:content</class>
                        <mandatory enforced="false">false</mandatory>
                        <many>false</many>
                    </target>
                </association>
            </associations>
            <overrides/>
            <mandatory-aspects/>
        </type>
        <type name="pf:job">
            <title>Job Description</title>
            <parent>cm:content</parent>
            <properties>
                <property name="pf:jobId">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:jobExperience">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:jobTitle">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:jobCompany">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:jobLocation">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:jobType">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:jobSalary">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:jobReferenceCode">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:jobContactInfo">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:jobAboutJob">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
                <property name="pf:jobNoOfVaccancies">
                    <type>d:text</type>
                    <mandatory>false</mandatory>
                    <index enabled="true"/>
                </property>
            </properties>
            <associations>
                <association name="pf:jobAttachments">
                    <source>
                        <mandatory>false</mandatory>
                        <many>true</many>
                    </source>
                    <target>
                        <class>cm:content</class>
                        <mandatory enforced="false">false</mandatory>
                        <many>false</many>
                    </target>
                </association>
            </associations>
            <overrides/>
            <mandatory-aspects/>
        </type>
    </types>
    <aspects/>
</model>