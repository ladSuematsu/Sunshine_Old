package ladsoft.sunshine.listener;

/**
 * Created by suematsu on 5/9/15.
 */
public interface AsyncOperatorCallback<TResult> {

    public void onOperationCompleteSuccess(TResult result);

    public void onOperationCompleteError();

}
