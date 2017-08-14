package com.lemon.profiler.mappers.pagination;

import java.text.MessageFormat;

import com.lemon.profiler.core.pagination.GenericSearchResults;
import com.lemon.profiler.model.Profile;

public class UnprocessedProfileSearchResults<T> extends GenericSearchResults<Profile>{
	public static String getDataGridMessage(int start, int end, int total)
    {
        return MessageFormat.format("Displaying {0} to {1} Unprocessed Profiles of {2}", start, end, total);
    }
}
