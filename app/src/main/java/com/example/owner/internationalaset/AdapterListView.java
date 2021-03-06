package com.example.owner.internationalaset;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.EventObject;

/*
    AdapterListView: listView adapter
 */
public class AdapterListView extends ArrayAdapter {
    ArrayList<?>  objects = null;
    ObjectEvent event = null;
    ObjectSession session = null;
    ObjectArticle article = null;
    ObjectKeynote keynote = null;
    ObjectPasscode passcode = null;
    ObjectUser user = null;
    ObjectAccount account = null;
    int resource;

    boolean span = false;
    TextView eventExp;
    TextView keynoteExp;

    public AdapterListView(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public AdapterListView(Context context, int resource, ArrayList<?> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.resource = resource;
    }

    public Object getObject(int position){
        return objects.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater i = LayoutInflater.from(getContext());
        View view = i.inflate(resource,null);

        if(getObject(position) instanceof String){
            TextView string = (TextView) view.findViewById(R.id.eventName);
            setTextView(string, (String)getObject(position));
        }

        if(getObject(position) instanceof ObjectEvent){
            event = (ObjectEvent)getObject(position);
            TextView eventName = (TextView) view.findViewById(R.id.eventName);
            TextView eventStartDate = (TextView) view.findViewById(R.id.eventStartDate);
            TextView eventEndDate = (TextView) view.findViewById(R.id.eventEndDate);
            final TextView eventDes = (TextView) view.findViewById(R.id.eventDes);
            TextView eventLocation = (TextView) view.findViewById(R.id.eventLocation);
            TextView eventVenue = (TextView) view.findViewById(R.id.eventVenue);
            ImageView eventImg = (ImageView) view.findViewById(R.id.eventImg);

            setTextView(eventName, event.getEventName());
            setTextView(eventStartDate, event.getEventStartDate());
            setTo(eventEndDate, event.getEventEndDate());
            setTextView(eventDes, event.getEventDes());
            setTextView(eventVenue,event.getEventVenue());
            setTextView(eventLocation, event.getEventLocation());

            //show image with Glide
            if (eventImg != null)
                Glide.with(getContext()).load(event.getEventImg()).into(eventImg);

            //expand textView listener
            View.OnClickListener eventListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (span == false){
                        eventDes.setMaxLines(3);
                        eventExp.setText("read more");
                        span = true;
                    }else{
                        eventDes.setMaxLines(eventDes.getLayout().getLineCount());
                        eventExp.setText("read less");
                        span = false;
                    }
                }
            };

            //set textView with expand listener
            if(eventDes != null){
                eventExp = (TextView) view.findViewById(R.id.eventExp);
                if(eventExp != null) {
                    eventExp.setVisibility(View.VISIBLE);
                    eventExp.setText("read more");
                    eventExp.setOnClickListener(eventListener);
                    eventDes.setOnClickListener(eventListener);
                }
            }
        }

        if(getObject(position) instanceof ObjectSession){
            session = (ObjectSession) getObject(position);
            TextView sessionDate = (TextView) view.findViewById(R.id.sessionDate);
            TextView sessionType = (TextView) view.findViewById(R.id.sessionType);
            TextView sessionName = (TextView) view.findViewById(R.id.sessionName);
            TextView sessionStartTime = (TextView) view.findViewById(R.id.sessionStartTime);
            TextView sessionEndTime = (TextView) view.findViewById(R.id.sessionEndTime);
            TextView sessionDes = (TextView) view.findViewById(R.id.sessionDes);
            setTextView(sessionType, session.getSessionType());
            setTextView(sessionName, session.getSessionName());
            setTextView(sessionStartTime, session.getSessionStartTime());
            setTextView(sessionEndTime, session.getSessionEndTime());
            setTextView(sessionDes, session.getSessionDes());
            setTextView(sessionDate, session.getSessionDate());
        }

        if(getObject(position) instanceof ObjectArticle) {
            article = (ObjectArticle)getObject(position);
            TextView articleName = (TextView) view.findViewById(R.id.articleName);
            TextView articlePresenter = (TextView) view.findViewById(R.id.articlePresenter);
            TextView articleAuthor = (TextView) view.findViewById(R.id.articleAuthor);
            TextView articleLocation = (TextView) view.findViewById(R.id.articleLocation);
            TextView articleStartTime = (TextView) view.findViewById(R.id.articleStartTime);
            TextView articleEndTime = (TextView) view.findViewById(R.id.articleEndTime);
            setTextView(articleName, article.getArticleName());
            setPresenter(articlePresenter, article.getArticlePresenter());
            setAuthor(articleAuthor, article.getArticleAuthor());
            setTextView(articleLocation, article.getArticleLocation());
            setTextView(articleStartTime, article.getArticleStartTime());
            setTextView(articleEndTime, article.getArticleEndTime());
        }

        if(getObject(position) instanceof ObjectKeynote){
            keynote = (ObjectKeynote) getObject(position);
            TextView keynoteName = (TextView) view.findViewById(R.id.keynoteName);
            TextView keynotePresenter = (TextView) view.findViewById(R.id.keynotePresenter);
            TextView keynoteInstitution = (TextView) view.findViewById(R.id.keynoteInstitution);
            final TextView keynoteDes = (TextView) view.findViewById(R.id.keynoteDes);
            TextView keynoteStartTime = (TextView) view.findViewById(R.id.keynoteStartTime);
            TextView keynoteEndTime = (TextView) view.findViewById(R.id.keynoteEndTime);
            ImageView keynoteImg = (ImageView) view.findViewById(R.id.keynoteImge);
            setTextView(keynoteName, keynote.getKeynoteName());
            setTextView(keynotePresenter, keynote.getKeynotePresenter());
            setTextView(keynoteInstitution, keynote.getKeynoteInstitution());
            setTextView(keynoteDes, keynote.getKeynoteDes());
            setTextView(keynoteStartTime, keynote.getKeynoteStartTime());
            setTo(keynoteEndTime, keynote.getKeynoteEndTime());

            //show image with Glide
            if (keynoteImg != null)
                Glide.with(getContext()).load(keynote.getKeynoteImg()).into(keynoteImg);

            //expand textView listener
            View.OnClickListener keynoteListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (span == false){
                        keynoteDes.setMaxLines(3);
                        keynoteExp.setText("read more");
                        span = true;
                    }else{
                        keynoteDes.setMaxLines(keynoteDes.getLayout().getLineCount());
                        keynoteExp.setText("read less");
                        span = false;
                    }
                }
            };

            //set textView with expand listener
            if(keynoteDes != null){
                keynoteExp = (TextView) view.findViewById(R.id.keynoteExp);
                if(keynoteExp != null) {
                    keynoteExp.setVisibility(View.VISIBLE);
                    keynoteExp.setText("read more");
                    keynoteExp.setOnClickListener(keynoteListener);
                    keynoteDes.setOnClickListener(keynoteListener);
                }
            }
        }

        if(getObject(position) instanceof ObjectPasscode) {
            passcode = (ObjectPasscode) getObject(position);
            TextView passcodeName = (TextView) view.findViewById(R.id.passcodeName);
            TextView passcodeCode = (TextView) view.findViewById(R.id.passcodeCode);
            TextView passcodeType = (TextView) view.findViewById(R.id.passcodeType);
            setTextView(passcodeName, passcode.getPasscodeName());
            setPasscode(passcodeCode, passcode.getPasscodeCode());
            setTextView(passcodeType,passcode.getPasscodeType());
        }

        if(getObject(position) instanceof ObjectAccount){
            account = (ObjectAccount) getObject(position);
            TextView accountEmail = (TextView) view.findViewById(R.id.accountEmail);
            TextView accountPassword = (TextView) view.findViewById(R.id.accountPassword);
            setEmail(accountEmail, account.getAccountEmail());
            setPassword(accountPassword, account.getAccountPassword());
        }

        if(getObject(position) instanceof ObjectUser){
            user = (ObjectUser) getObject(position);
            TextView userFirstName = (TextView) view.findViewById(R.id.userFirstName);
            TextView userMiddleName = (TextView) view.findViewById(R.id.userMiddleName);
            TextView userLastName = (TextView) view.findViewById(R.id.userLastName);
            TextView userInstitution = (TextView) view.findViewById(R.id.userInstitution);
            TextView userCountry = (TextView) view.findViewById(R.id.userCountry);
            setName(userFirstName,user.getUserFirstName());
            setName(userMiddleName,user.getUserMiddleName());
            setTextView(userLastName,user.getUserLastName());
            setTextView(userInstitution,user.getUserInstitution());
            setTextView(userCountry,user.getUserCountry());
        }
        return view;
    }

    public void setTextView(TextView text, String string){
        if(text != null){
            text.setText("");
            if(!string.equals("")){
                text.setText(string);
            }
        }
    }

    public void setTo(TextView text, String string){
        if(text != null){
            text.setText("");
            if(!string.equals("")){
                text.setText(" To " + string);
            }
        }
    }

    public void setPresenter(TextView text, String string){
        if(text != null){
            text.setText("");
            if(!string.equals("")){
                text.setText("Presenter: " + string);
            }
        }
    }

    public void setAuthor(TextView text, String string){
        if(text != null){
            text.setText("");
            if(!string.equals("")){
                text.setText("Author: " + string);
            }
        }
    }

    public void setPasscode(TextView text, String string){
        if(text != null){
            text.setText("");
            if(!string.equals("")){
                text.setText("Passcode: " + string);
            }
        }
    }

    public void setName(TextView text, String string){
        if(text != null){
            text.setText("");
            if(!string.equals("")){
                text.setText(string + " ");
            }
        }
    }

    public void setEmail(TextView text, String string){
        if(text != null){
            text.setText("");
            if(!string.equals("")){
                text.setText("Email: " + string);
            }
        }
    }

    public void setPassword(TextView text, String string){
        if(text != null){
            text.setText("");
            if(!string.equals("")){
                text.setText("Password: " + string);
            }
        }
    }

}
